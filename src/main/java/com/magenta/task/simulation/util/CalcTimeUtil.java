package com.magenta.task.simulation.util;

import com.magenta.task.simulation.model.Order;
import com.magenta.task.simulation.model.PointInMap;
import com.magenta.task.simulation.model.Resource;

import java.time.LocalTime;
import java.util.List;

public class CalcTimeUtil {

    //Определяем время движения от точки 1 до точки 2
    public static int calcDrivingTime(Resource resource, PointInMap point1, PointInMap point2) {
        double distance = DistanceUtil.calculate(point1, point2);
        return (int) (distance / resource.getSpeed() * 60);
    }

    //Определяем суммарное время загрузки заказов данного рейса. мин
    public static int calcTimeToLoadingThisFlight(List<Order> orderList) {
        final int[] sum = {0};

        orderList.forEach(order -> sum[0] = sum[0] + order.getLoadingTime());

        return sum[0];
    }

    //Определяем время начала загрузки заказов данного рейса
    public static LocalTime calcTimeToStartLoadingThisFlight(LocalTime timeStart1TimeWindow, int timeToLoadingThisFlight,
                                                             LocalTime timeStartWorkDC, int drivingTimePointToPoint) {
        //T- время
        // Т начала загрузки = Т начало первого временного окна - Т суммарное время загрузки рейса  - Т пути

        LocalTime timeToStartLoadingThisFlight =
                timeStart1TimeWindow.minusMinutes(timeToLoadingThisFlight).minusMinutes(drivingTimePointToPoint);
        // Если выбранное время начала загрузки раньше начала работы DC возвращаем время начала работы DC
        return timeToStartLoadingThisFlight.isAfter(timeStartWorkDC) ? timeToStartLoadingThisFlight : timeStartWorkDC;
    }

    //Определяем время выезда из DC
    public static LocalTime calcTimeGoToOutDC(LocalTime timeToStartLoadingThisFlight, int timeToLoadingThisFlight) {
        //T- время
        // Т выезда = Т нач.загрузки + Т загрузки

        return timeToStartLoadingThisFlight.plusMinutes(timeToLoadingThisFlight);
    }

    //Определяем время начала разгрузки заказа Т нач. разгр = Т нач.выезда из DC + Т пути
    public static LocalTime calcTimeToStartUnloadingFirstOrder(LocalTime timeStartOutDC, int drivingTime) {
        return timeStartOutDC.plusMinutes(drivingTime);
    }

    //Определяем время окончания разгрузки заказа у клиента Т оконч. разгр = Т нач. разгр. у клиента + Т разгрузки
    public static LocalTime calcTimeToEndUnloadingFirstOrder(LocalTime timeToStartUnloadingFirstOrder, int timeUnloading) {
        return timeToStartUnloadingFirstOrder.plusMinutes(timeUnloading);
    }

    //Определяем время начала разгрузки заказа Т нач.разгр = Т оконч. разгр. пред. заказа  + Т пути
    public static LocalTime calcTimeToStartUnloadingOrder(LocalTime timeStartCurrentTimeWindow,
                                                          LocalTime timeToEndUnloadingPreviousOrder,
                                                          int timeDrivingToOrder) {
        LocalTime time = timeToEndUnloadingPreviousOrder.plusMinutes(timeDrivingToOrder);
        // Если ресурс доедет раньше начала временного окна возвращаем время начала временного окна
        return time.isBefore(timeStartCurrentTimeWindow) ? timeStartCurrentTimeWindow : time;
    }

    //Определяем время окончания разгрузки заказа у клиента Т оконч. разгр. = Тнач.разгр. у клиента + Т разгрузки
    public static LocalTime calcTimeToEndUnloadingOrder(LocalTime timeToStartUnloadingCurrentOrder, int timeUnloading) {
        return timeToStartUnloadingCurrentOrder.plusMinutes(timeUnloading);
    }

    //Определяем время возвращения в распред. центр DC Т возращ.  = Токонч.разгр. последн заказа + Т пути
    public static LocalTime calcTimeToReturnInDC(LocalTime timeToEndUnloadingCurrentOrder, int drivingTime) {
        return timeToEndUnloadingCurrentOrder.plusMinutes(drivingTime);
    }
}
