package com.magenta.task.simulation.dataMock;

import com.magenta.task.simulation.model.*;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ScheduleData {
    // Депо Минская 25
    public final static PointInMap DC = new PointInMap(53.232244, 50.250508);
    // Первый заказ ул. Ульяновская ул. Садовая
    public final static PointInMap POINT1 = new PointInMap(53.198343, 50.111600);
    // Второй заказ ул. Кабельная 7а
    public final static PointInMap POINT2 = new PointInMap(53.188697, 50.262046);
    // Третий заказ ул. Смышляевское шоссе
    public final static PointInMap POINT3 = new PointInMap(53.251370, 50.368399);

    public static final TimeWindow TIME_WINDOW1 = new TimeWindow(LocalTime.of(10, 0), LocalTime.of(11, 0));

    public static final TimeWindow TIME_WINDOW2 = new TimeWindow(LocalTime.of(12, 0), LocalTime.of(13, 0));

    public static final TimeWindow TIME_WINDOW3 = new TimeWindow(LocalTime.of(13, 0), LocalTime.of(18, 0));

    public static final Work WORK1 = new Work(LocalTime.of(10, 0), LocalTime.of(10, 20));

    public static final Work WORK2 = new Work(LocalTime.of(12, 0), LocalTime.of(12, 20));

    public static final Work WORK3 = new Work(LocalTime.of(13, 19), LocalTime.of(13, 39));

    public static final Schedule SCHEDULE = new Schedule( );

    static {
        SCHEDULE.setTimeGoToOutDC(LocalTime.of(9, 1));
        SCHEDULE.setTimeToReturnInDC(LocalTime.of(14, 27));
        SCHEDULE.addWork(WORK1);
        SCHEDULE.addWork(WORK2);
        SCHEDULE.addWork(WORK3);
    }

    public final static Order ORDER1 = new Order(POINT1, 10, TIME_WINDOW1, 20, 20);

    public final static Order ORDER2 = new Order(POINT2, 10, TIME_WINDOW2, 20, 20);

    public final static Order ORDER3 = new Order(POINT3, 10, TIME_WINDOW3, 20, 20);


    public final static List<Order> ORDER_LIST = new ArrayList<>();

    static {
        ORDER_LIST.add(ORDER1);
        ORDER_LIST.add(ORDER2);
        ORDER_LIST.add(ORDER3);
    }

    public final static Resource UAZ_452 = new Resource(10, 40);

    public static List<Order> getOrders() {
        return ORDER_LIST;
    }

    public static final int DISTANCE_DC_POINT1 = 10; // км

    public static final int DRIVING_TIME_DC_POINT1 = 59; // мин

    public static final int TIME_TO_LOADING_THIS_FLIGHT = 60; // мин

    public static final LocalTime TIME_START_1_TIME_WINDOW = LocalTime.of(10, 0);

    public static final LocalTime TIME_START_WORK_DC = LocalTime.of(8, 0);

    public static final LocalTime TIME_TO_START_LOADING_THIS_FLIGHT = LocalTime.of(8, 1);

    public static final LocalTime TIME_GO_TO_OUT_DC = LocalTime.of(9, 1);

    public static final LocalTime TIME_TO_START_UNLOADING_FIRST_ORDER = LocalTime.of(10, 0);

    public static final int TIME_TO_UNLOADING_FIRST_ORDER = 20;

    public static final LocalTime TIME_TO_END_UNLOADING_FIRST_ORDER = LocalTime.of(10, 20);

    public static final LocalTime TIME_TO_START_UNLOADING_PREVIOUS_ORDER = LocalTime.of(10, 20);

    public static final int DRIVING_TIME_DC_POINT2 = 60; // мин

    public static final LocalTime TIME_START_2_TIME_WINDOW = LocalTime.of(12, 0);

    public static final LocalTime TIME_TO_START_UNLOADING_ORDER = LocalTime.of(12, 0);

    public static final int TIME_TO_UNLOADING_CURRENT_ORDER = 20;

    public static final LocalTime TIME_TO_END_UNLOADING_CURRENT_ORDER = LocalTime.of(12, 20);

    public static final LocalTime TIME_TO_RETURN_IN_DC = LocalTime.of(13, 19);


}
