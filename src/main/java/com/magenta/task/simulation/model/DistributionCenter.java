package com.magenta.task.simulation.model;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class DistributionCenter {

    private final PointInMap coordinates; //Координаты: (Широта, Долгота)

    private final TimeWindow timeWindowDC; // Окно доступности

    private final Map<Integer, Resource> fleet = new HashMap<>(); // Число ресурсов

}
