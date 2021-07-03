package com.magenta.task.simulation;

import com.magenta.task.simulation.model.Order;
import com.magenta.task.simulation.model.Resource;
import com.magenta.task.simulation.model.Schedule;
import com.magenta.task.simulation.model.Work;

import java.time.LocalTime;
import java.util.List;

public interface SimulationAlgorithm {

    Schedule simulate(List<Order> orderList, Resource resource, LocalTime startWorkDC, LocalTime endWorkDC);

}
