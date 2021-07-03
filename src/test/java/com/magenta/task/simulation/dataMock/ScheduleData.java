package com.magenta.task.simulation.dataMock;

import com.magenta.task.simulation.model.*;

import java.time.LocalTime;

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

    public static final TimeWindow TIME_WINDOW3 = new TimeWindow(LocalTime.of(15, 0), LocalTime.of(18, 0));

    public static final Work WORK1 = new Work(LocalTime.of(10, 0), LocalTime.of(10, 20));

    public static final Work WORK2 = new Work(LocalTime.of(12, 0), LocalTime.of(12, 20));

    public static final Work WORK3 = new Work(LocalTime.of(13, 20), LocalTime.of(13, 40));

    public static final Schedule SCHEDULE = new Schedule(LocalTime.of(9, 0), LocalTime.of(14, 28));

    static {
        SCHEDULE.addWork(WORK1);
        SCHEDULE.addWork(WORK2);
        SCHEDULE.addWork(WORK3);
    }

    public final static Order ORDER1 = new Order(POINT1, 10, TIME_WINDOW1, 20, 20);

    public final static Order ORDER2 = new Order(POINT2, 10, TIME_WINDOW2, 20, 20);

    public final static Order ORDER3 = new Order(POINT3, 10, TIME_WINDOW3, 20, 20);

    public final static Resource UAZ_452 = new Resource(10, 40);
}
