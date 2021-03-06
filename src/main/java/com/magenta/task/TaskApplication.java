package com.magenta.task;

import com.magenta.task.simulation.SimulationAlgorithm;
import com.magenta.task.simulation.SimulationAlgorithmSequential;
import com.magenta.task.simulation.model.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@EnableCaching
public class TaskApplication {

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
    public final static Resource UAZ_452 = new Resource(20, 40);

    // Время работы
    public static final TimeWindow timeWindow = new TimeWindow(LocalTime.of(8, 0),
            LocalTime.of(22, 0));

    // Распределительный центр
    public static final DistributionCenter DISTRIBUTION_CENTER = new DistributionCenter(DC, timeWindow);

    static {
        DISTRIBUTION_CENTER.addResource(UAZ_452);
    }

    public static void main(String[] args) {
        SpringApplication.run(TaskApplication.class, args);

        SimulationAlgorithm simulationAlgorithm = new SimulationAlgorithmSequential();

        simulationAlgorithm.simulate(ORDER_LIST, UAZ_452, DISTRIBUTION_CENTER);

    }
}
