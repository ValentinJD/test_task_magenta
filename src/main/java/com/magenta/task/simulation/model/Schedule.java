package com.magenta.task.simulation.model;

import lombok.Data;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class Schedule {

    private final List<Work> workList = new ArrayList<>(); //список работ

    private LocalTime timeGoToOutDC; // время выезда из депо

    private LocalTime timeToReturnInDC; // время возвращения в депо

    public void addWork(Work work) {
        workList.add(work);
    }
}
