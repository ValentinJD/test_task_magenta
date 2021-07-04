package com.magenta.task.simulation;

import com.magenta.task.simulation.model.DistributionCenter;
import com.magenta.task.simulation.model.Order;
import com.magenta.task.simulation.model.Resource;
import com.magenta.task.simulation.model.Schedule;

import java.time.LocalTime;
import java.util.List;

// Основной интерфейс алгоритма симуляции
public interface SimulationAlgorithm {

    Schedule simulate(List<Order> orderList, Resource resource, DistributionCenter DC);

}
