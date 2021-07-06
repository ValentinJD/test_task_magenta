package com.magenta.task.simulation.dataMock;

import com.magenta.task.simulation.model.*;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

// Тестовые данные для расписания работ
public class ScheduleData {
    // Депо Самара, Минская 25
    public final static PointInMap DC = new PointInMap(53.232086357168356, 50.2504762918857); // 53.232086357168356, 50.2504762918857
    // Первый заказ Самара, ул. Ульяновская ул. Садовая
    public final static PointInMap POINT1 = new PointInMap(53.198343, 50.111600);
    // Второй заказ Самара, ул. Кабельная 7а
    public final static PointInMap POINT2 = new PointInMap(53.188697, 50.262046);
    // Третий заказ Самара, ул. Смышляевское шоссе ул. Аэропорт 2, 1-7, Самара, Самарская обл., 443046
    public final static PointInMap POINT3 = new PointInMap(53.25152887005768, 50.3748212627859);

    // Временные окна соответствующих заказов
    public static final TimeWindow TIME_WINDOW1 = new TimeWindow(LocalTime.of(10, 0), LocalTime.of(11, 0));
    public static final TimeWindow TIME_WINDOW2 = new TimeWindow(LocalTime.of(12, 0), LocalTime.of(13, 0));
    public static final TimeWindow TIME_WINDOW3 = new TimeWindow(LocalTime.of(13, 0), LocalTime.of(18, 0));

    // Перечень работ по заказам
    public static final Work WORK1 = new Work(LocalTime.of(10, 0), LocalTime.of(10, 20));
    public static final Work WORK2 = new Work(LocalTime.of(12, 0), LocalTime.of(12, 20));
    public static final Work WORK3 = new Work(LocalTime.of(13, 0), LocalTime.of(13, 20));

    // Расписание работ
    public static final Schedule SCHEDULE = new Schedule();

    static {
        SCHEDULE.setTimeGoToOutDC(LocalTime.of(9, 23)); // время выезда из распределительного центра
        SCHEDULE.setTimeToReturnInDC(LocalTime.of(13, 51)); // время возвращения в распределительный центр
        SCHEDULE.addWork(WORK1);
        SCHEDULE.addWork(WORK2);
        SCHEDULE.addWork(WORK3);
    }

    // Перечень заказов
    public final static Order ORDER1 = new Order(POINT1, 10, TIME_WINDOW1, 20, 20);
    public final static Order ORDER2 = new Order(POINT2, 10, TIME_WINDOW2, 20, 20);
    public final static Order ORDER3 = new Order(POINT3, 10, TIME_WINDOW3, 20, 20);

    public final static List<Order> ORDER_LIST = new ArrayList<>();

    static {
        ORDER_LIST.add(ORDER1);
        ORDER_LIST.add(ORDER2);
        ORDER_LIST.add(ORDER3);
    }

    // Ресурс УАЗ-452 скорость движения 20 км/ч вместимость 40 кг
    public final static Resource UAZ_452 = new Resource(20, 40);

    public static List<Order> getOrders() {
        return ORDER_LIST;
    }

    // Расстояние от распределительного центра до первого заказа
    public static final int DISTANCE_DC_POINT1 = 12; // км

    // Время в пути до от DC до первого заказа, мин
    public static final int DRIVING_TIME_DC_POINT1 = 37;

    // Продолжительность загрузки всех заказов данного рейса мин
    public static final int TIME_TO_LOADING_THIS_FLIGHT = 60;

    // Время начала первого окна
    public static final LocalTime TIME_START_1_TIME_WINDOW = LocalTime.of(10, 0);

    // Время начала работы DC
    public static final LocalTime TIME_START_WORK_DC = LocalTime.of(8, 0);

    // Время начала загрузки в DC
    public static final LocalTime TIME_TO_START_LOADING_THIS_FLIGHT = LocalTime.of(8, 23);

    // Время выезда из DC после загрузки
    public static final LocalTime TIME_GO_TO_OUT_DC = LocalTime.of(9, 23);

    // Время начала разгрузки первого заказа
    public static final LocalTime TIME_TO_START_UNLOADING_FIRST_ORDER = LocalTime.of(10, 0);

    // Продолжительность разгрузки первого заказа, мин
    public static final int TIME_TO_UNLOADING_FIRST_ORDER = 20;

    // Время окончания разгрузки первого заказа
    public static final LocalTime TIME_TO_END_UNLOADING_FIRST_ORDER = LocalTime.of(10, 20);

    // Продолжительность движения от первого до второго заказа
    public static final int DRIVING_TIME_POINT1_POINT2 = 37; // мин

    // Начало второго временного окна
    public static final LocalTime TIME_START_2_TIME_WINDOW = LocalTime.of(12, 0);

    // Время начала разгрузки заказа
    public static final LocalTime TIME_TO_START_UNLOADING_ORDER = LocalTime.of(12, 0);

    // Продолжительность разгрузки текущего заказа
    public static final int TIME_TO_UNLOADING_CURRENT_ORDER = 20;

    // Время окончания разгрузки текущего заказа
    public static final LocalTime TIME_TO_END_UNLOADING_CURRENT_ORDER = LocalTime.of(12, 20);

    // Время окончания разгрузки последнего заказа
    public static final LocalTime TIME_TO_END_UNLOADING_LAST_ORDER = LocalTime.of(13, 20);

    // Время возвращения в распределительный центр DC
    public static final LocalTime TIME_TO_RETURN_IN_DC = LocalTime.of(13, 51);

    // Продолжительность движения от третьего заказа до распределительного центра DC
    public static final int DRIVING_TIME_POINT3_DC = 31; // мин

    // Время работы
    public static final TimeWindow timeWindow = new TimeWindow(LocalTime.of(8, 0),
            LocalTime.of(22, 0));

    // Распределительный центр
    public static final DistributionCenter DISTRIBUTION_CENTER = new DistributionCenter(DC, timeWindow);

    static {
        DISTRIBUTION_CENTER.addResource(UAZ_452);
    }
}
