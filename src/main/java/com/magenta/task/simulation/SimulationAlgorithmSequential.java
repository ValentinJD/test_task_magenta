package com.magenta.task.simulation;

import com.magenta.task.simulation.exception.EmptyOrderException;
import com.magenta.task.simulation.model.*;
import com.magenta.task.simulation.util.CalcTimeUtil;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.util.List;

// Реализация алгоритма симуляции
// Последовательное вычисление
@Component
public class SimulationAlgorithmSequential implements SimulationAlgorithm {

    // Координаты распределительного центра
    private final PointInMap pointDC = new PointInMap(53.232244, 50.250508);
    private final TimeWindow timeWindow = new TimeWindow(LocalTime.of(8, 0),
            LocalTime.of(22, 0));
    private final Resource resource = new Resource(10, 40);
    private final DistributionCenter DC = new DistributionCenter(pointDC, timeWindow);

    {
        DC.addResource(resource);
    }

    // Метод симуляции возвращает расписание работ для ресурса
    @Override
    public Schedule simulate(List<Order> orderList, Resource resource, DistributionCenter DC) {
        Schedule schedule = new Schedule();

        // Вычисляем время выезда из распределительного центра
        setTimeGoToOutDC(schedule, orderList, resource, DC.getTimeWindowDC().getStart());

        if (orderList.size() != 0) {
            // Определяем работу по первому заказу
            setFirstWork(orderList, resource, schedule);
        } else {
            throw new EmptyOrderException("Empty list orders");
        }

        if (orderList.size() == 1) {
            // Устанавливаем время возращения в распределительный центр
            setTimeReturnToDC(resource, orderList, schedule);

            return schedule;
        }

        Order previousOrder = orderList.get(0);

        // Вычисляем время начала и окончания остальных заказов
        for (int i = 1; i < orderList.size(); i++) {

            Order currentOrder = orderList.get(i);

            // Время в пути от предыдущего заказа до текущего
            int drivingTime = CalcTimeUtil.calcDrivingTime(resource, previousOrder.getPointInMap(),
                    currentOrder.getPointInMap());

            List<Work> workList = schedule.getWorkList();

            // Время начала разгрузки заказа
            LocalTime timeStartUnloadingOrder = CalcTimeUtil.calcTimeToStartUnloadingOrder(
                    currentOrder.getTimeWindow().getStart(), workList.get(i - 1).getTimeToFinish(), drivingTime);

            // Время окончания разгрузки заказа
            LocalTime timeEndUnloadingOrder = CalcTimeUtil.calcTimeToEndUnloadingOrder(timeStartUnloadingOrder,
                    currentOrder.getTimeForUnloading());

            Work work = new Work(timeStartUnloadingOrder, timeEndUnloadingOrder);

            schedule.addWork(work);

            previousOrder = currentOrder;
        }

        // Определяем время возвращения в распределительный центр DC
        setTimeReturnToDC(resource, orderList, schedule);

        return schedule;
    }

    // Определяем время выезда из расределительного центра
    public void setTimeGoToOutDC(Schedule schedule, List<Order> orderList, Resource resource, LocalTime startWorkDC) {

        // Время загрузки всех заказов данного рейса
        int timeToLoadingThisFlight = CalcTimeUtil.calcTimeToLoadingThisFlight(orderList);

        Order firstOrder = orderList.get(0);

        // Время в пути от распределительного центра до первого заказа
        int calcDrivingTime = CalcTimeUtil.calcDrivingTime(resource, DC.getCoordinates(), firstOrder.getPointInMap());

        // Время начала загрузки в распределительном центре DC
        LocalTime timeToStartLoadingThisFlight =
                CalcTimeUtil.calcTimeToStartLoadingThisFlight(firstOrder.getTimeWindow().getStart(), timeToLoadingThisFlight,
                        startWorkDC, calcDrivingTime);

        // Время выезда из распределительного центра
        LocalTime timeGoToOutDC = CalcTimeUtil.calcTimeGoToOutDC(timeToStartLoadingThisFlight, timeToLoadingThisFlight);

        schedule.setTimeGoToOutDC(timeGoToOutDC);

    }

    // Определяем время выезда в распределительный центр
    public void setTimeReturnToDC(Resource resource, List<Order> orderList, Schedule schedule) {
        List<Work> workList = schedule.getWorkList();

        // Время в пути от последнего заказа до распределительного центра
        int drivingTimeFormOrderToDC = CalcTimeUtil.calcDrivingTime(resource,
                orderList.get(orderList.size() - 1).getPointInMap(), DC.getCoordinates());

        // Время возвращения в распределительный центр
        LocalTime timeToReturnInDC = CalcTimeUtil.calcTimeToReturnInDC(workList.get(workList.size() - 1).getTimeToFinish(),
                drivingTimeFormOrderToDC);

        schedule.setTimeToReturnInDC(timeToReturnInDC);
    }

    // Определяем время выполненмя первого заказа
    public void setFirstWork(List<Order> orderList, Resource resource, Schedule schedule) {

        Order firsOrder = orderList.get(0);

        // Время в пути от распределительного центра до первого заказа
        int drivingTime = CalcTimeUtil.calcDrivingTime(resource, DC.getCoordinates(), firsOrder.getPointInMap());

        // Время начала разгрузки первого заказа
        LocalTime timeToStartUnloadingFirstOrder = CalcTimeUtil.calcTimeToStartUnloadingFirstOrder(
                schedule.getTimeGoToOutDC(), drivingTime);

        // Время окончания разгрузки первого заказа
        LocalTime timeToEndUnloadingFirstOrder = CalcTimeUtil.calcTimeToEndUnloadingFirstOrder(timeToStartUnloadingFirstOrder,
                firsOrder.getTimeForUnloading());

        Work work = new Work(timeToStartUnloadingFirstOrder, timeToEndUnloadingFirstOrder);

        schedule.addWork(work);
    }
}
