package com.magenta.task.simulation.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Schedule {

    private final List<TimeWindow> timeWindowList = new ArrayList<>();
}
