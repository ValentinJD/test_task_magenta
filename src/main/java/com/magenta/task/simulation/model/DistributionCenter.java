package com.magenta.task.simulation.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Распределительный центр DC
@Data
public class DistributionCenter {

    private final PointInMap coordinates; //Координаты: (Широта, Долгота)

    private final TimeWindow timeWindowDC; // Окно доступности

    private final List<Resource> fleet = new ArrayList<>(); // Число ресурсов

    public void addResource(Resource resource) {
        fleet.add(resource);
    }

}
