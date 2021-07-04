package com.magenta.task.simulation;

import com.magenta.task.simulation.exception.EmptyOrderException;
import com.magenta.task.simulation.model.*;
import com.magenta.task.simulation.util.CalcTimeUtil;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class SimulationAlgorithmSequential implements SimulationAlgorithm {

    private final PointInMap pointDC = new PointInMap(53.232244, 50.250508);


    @Override
    public Schedule simulate(List<Order> orderList, Resource resource, LocalTime startWorkDC, LocalTime endWorkDC) {
        Schedule schedule = new Schedule();

        // устанавливаем время выезда из распределительного центра
        setTimeGoToOutDC(schedule, orderList, resource, startWorkDC);

        if (orderList.size() != 0) {
            // устанавливаем работу по первому заказу
            setFirstWork(orderList, resource, schedule);
        } else {
            throw new EmptyOrderException("Empty list orders");
        }

        if (orderList.size() == 1) {
            // устанавливаем время возращения в распределительный центр
            setTimeReturnToDC(resource, orderList, schedule);

            return schedule;
        }
        Order previousOrder = orderList.get(0);
        // Вычисляем время начала и окончания остальных заказов
        for (int i = 1; i < orderList.size(); i++) {


            Order currentOrder = orderList.get(i);

            int drivingTime = CalcTimeUtil.calcDrivingTime(resource, previousOrder.getPointInMap(),
                    currentOrder.getPointInMap());

            List<Work> workList = schedule.getWorkList();

            LocalTime timeStartUnloadingOrder = CalcTimeUtil.calcTimeToStartUnloadingOrder(
                    currentOrder.getTimeWindow().getStart(), workList.get(i-1).getTimeToFinish(), drivingTime);

            LocalTime timeEndUnloadingOrder = CalcTimeUtil.calcTimeToEndUnloadingOrder(timeStartUnloadingOrder,
                    currentOrder.getTimeForUnloading());

            Work work = new Work(timeStartUnloadingOrder, timeEndUnloadingOrder);

            schedule.addWork(work);

            previousOrder = currentOrder;
        }

        setTimeReturnToDC(resource, orderList, schedule);

        return schedule;
    }


    public void setTimeGoToOutDC(Schedule schedule, List<Order> orderList, Resource resource, LocalTime startWorkDC) {

        int timeToLoadingThisFlight = CalcTimeUtil.calcTimeToLoadingThisFlight(orderList);

        Order firstOrder = orderList.get(0);

        int calcDrivingTime = CalcTimeUtil.calcDrivingTime(resource, pointDC, firstOrder.getPointInMap());

        LocalTime timeToStartLoadingThisFlight =
                CalcTimeUtil.calcTimeToStartLoadingThisFlight(firstOrder.getTimeWindow().getStart(), timeToLoadingThisFlight,
                        startWorkDC, calcDrivingTime);

        LocalTime timeGoToOutDC = CalcTimeUtil.calcTimeGoToOutDC(timeToStartLoadingThisFlight, timeToLoadingThisFlight);

        schedule.setTimeGoToOutDC(timeGoToOutDC);

    }

    public void setTimeReturnToDC(Resource resource, List<Order> orderList, Schedule schedule) {
        List<Work> workList = schedule.getWorkList();

        int drivingTimeFormOrderToDC = CalcTimeUtil.calcDrivingTime(resource, orderList.get(orderList.size()-1).getPointInMap(), pointDC);



        LocalTime timeToReturnInDC = CalcTimeUtil.calcTimeToReturnInDC(workList.get(workList.size()-1).getTimeToFinish(),
                drivingTimeFormOrderToDC);

        schedule.setTimeToReturnInDC(timeToReturnInDC);
    }

    public void setFirstWork(List<Order> orderList, Resource resource, Schedule schedule) {


        Order firsOrder = orderList.get(0);

        int drivingTime = CalcTimeUtil.calcDrivingTime(resource, pointDC, firsOrder.getPointInMap());

        // рассчитываем время начала погрузки первого клиента
        LocalTime timeToStartUnloadingFirstOrder = CalcTimeUtil.calcTimeToStartUnloadingFirstOrder(
                schedule.getTimeGoToOutDC(), drivingTime);

        LocalTime timeToEndUnloadingFirstOrder = CalcTimeUtil.calcTimeToEndUnloadingFirstOrder(timeToStartUnloadingFirstOrder,
                firsOrder.getTimeForUnloading());

        Work work = new Work(timeToStartUnloadingFirstOrder, timeToEndUnloadingFirstOrder);

        schedule.addWork(work);
    }







    /*
     * Описание алгоритма
      1. Определяем время движения от распр. центра до первого клиента Тдв.1
      2. Определяем суммарное время загрузки заказов данного рейса. Тсум.
      3. Определяем время начала загрузки заказов данного рейса
      Тнач. заг. = Тнач.1окна - Тсум. - Тдв.1
      Условие
      Tнач.р.см. < Тнач.заг
      Нет
      Устанавливаем время начала загрузки заказов данного рейса
      Тнач. заг. = Тнач. раб. распрд. центра
      Да
      Время начала загрузки заказов данного рейса определено Тнач. заг.
      Определяем время в пути Т пути

      Определяем время начала разгрузки заказа у клиента Тнач.разгр = Твыезда из р.ц. + Т пути 1-й заказ
      Определяем время окончания разгрузки 1-го заказа у клиента Тнач.разгр = Тнач.разгр. у клиента + Т разгрузки
      Условие Еще есть заказы ?
      Нет возвращаем результат

     * Да
     * Определяем время в пути Т пути
     * т. 1 Определяем время начала разгрузки заказа Тнач.разгр = Токонч. разгр. пред. заказа  + Т пути
     * Условие Тнач.разгр < Т нач. окна
     * Нет
     * Тнач.разгр  определено
     * Определяем время окончания разгрузки заказа Токонч.разгр = Тначал. разгр.   + Т разгрузки
     * ДА
     * Тнач.разгр = Т нач. окна
     * Условие Еще есть заказы ?
     * Да возвращаемся в т.1
     * Нет
     * Определяем время возвращения в распрд. центр
     * Твозвр.в р.ц. = Токонч. разгр. пред. заказа  + Т пути
     * Возвращаем результат     * */
}
