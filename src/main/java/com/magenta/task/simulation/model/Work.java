package com.magenta.task.simulation.model;

import lombok.Data;

import java.time.LocalTime;

// Работа
@Data
public class Work {

    private final LocalTime timeToStart; // время начала разгрузки у клиента

    private final LocalTime timeToFinish; // время окончания разгрузки у клиента

}
