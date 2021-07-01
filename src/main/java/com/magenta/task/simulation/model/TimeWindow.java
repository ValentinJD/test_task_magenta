package com.magenta.task.simulation.model;

import lombok.Data;

import java.time.LocalTime;

@Data
public class TimeWindow {

    private final LocalTime start; // начало временного окна доступности

    private final LocalTime end; // окончание временного окна доступности


}
