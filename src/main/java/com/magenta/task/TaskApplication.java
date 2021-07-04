package com.magenta.task;

import com.magenta.task.simulation.SimulationAlgorithm;
import com.magenta.task.simulation.SimulationAlgorithmSequential;
import com.magenta.task.simulation.model.*;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class TaskApplication {

    // Депо Самара, Минская 25
    public final static PointInMap DC = new PointInMap(53.232244, 50.250508);
    // Первый заказ Самара, ул. Ульяновская ул. Садовая
    public final static PointInMap POINT1 = new PointInMap(53.198343, 50.111600);
    // Второй заказ Самара, ул. Кабельная 7а
    public final static PointInMap POINT2 = new PointInMap(53.188697, 50.262046);
    // Третий заказ Самара, ул. Смышляевское шоссе
    public final static PointInMap POINT3 = new PointInMap(53.251370, 50.368399);

    // Временные окна соответствующих заказов
    public static final TimeWindow TIME_WINDOW1 = new TimeWindow(LocalTime.of(10, 0), LocalTime.of(11, 0));
    public static final TimeWindow TIME_WINDOW2 = new TimeWindow(LocalTime.of(12, 0), LocalTime.of(13, 0));
    public static final TimeWindow TIME_WINDOW3 = new TimeWindow(LocalTime.of(13, 0), LocalTime.of(18, 0));

    // Расписание работ
    public static final Schedule SCHEDULE = new Schedule();

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

    // Ресурс УАЗ-452 скорость движения 10 км/ч вместимость 40 кг
    public final static Resource UAZ_452 = new Resource(10, 40);

    // Время работы
    public static final TimeWindow timeWindow = new TimeWindow(LocalTime.of(8, 0),
            LocalTime.of(22, 0));

    // Распределительный центр
    public static final DistributionCenter DISTRIBUTION_CENTER = new DistributionCenter(DC, timeWindow);

    static {
        DISTRIBUTION_CENTER.addResource(UAZ_452);
    }

    public static void main(String[] args) {

        SimulationAlgorithm simulationAlgorithm = new SimulationAlgorithmSequential();

        simulationAlgorithm.simulate(ORDER_LIST, UAZ_452, DISTRIBUTION_CENTER);

    }
}
