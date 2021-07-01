package com.magenta.task.simulation.model;

import lombok.Data;

@Data
public class Order {

    private final PointInMap pointInMap; // Координаты: (Широта, Долгота)

    private final int weight; // Вес (кг)

    private final TimeWindow timeWindow; // Временное окно доступности

    private final int loadingTime; //Время, необходимое на погрузку заказа в распределительном центре (мин)

    private final int timeForUnloading; // Время, необходимое на разгрузку заказа у клиента (мин)

}
