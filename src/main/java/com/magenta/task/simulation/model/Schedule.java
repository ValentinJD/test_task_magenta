package com.magenta.task.simulation.model;

import lombok.Data;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class Schedule {

    private final List<TimeWindow> timeWindowList = new ArrayList<>(); //порядок объезда заказов

    private final LocalTime timeGoToDC; // время выезда в депо

    private final LocalTime timeToReturnInDC; // время возвращения в депо
}
