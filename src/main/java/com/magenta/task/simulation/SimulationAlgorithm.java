package com.magenta.task.simulation;

import com.magenta.task.simulation.model.Schedule;
import com.magenta.task.simulation.model.Work;

import java.util.List;

public interface SimulationAlgorithm {

    Schedule simulate(List<Work> workList);

}
