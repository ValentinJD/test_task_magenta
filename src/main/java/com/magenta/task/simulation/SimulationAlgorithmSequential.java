package com.magenta.task.simulation;

import com.magenta.task.simulation.model.*;
import com.magenta.task.simulation.util.DistanceUtil;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.util.List;

@Component
public class SimulationAlgorithmSequential implements SimulationAlgorithm {

    @Override
    public Schedule simulate(List<Order> orderList, Resource resource, LocalTime startWorkDC, LocalTime endWorkDC) {
        Schedule schedule = new Schedule();

        schedule.addWork(new Work(LocalTime.of(1,0), LocalTime.of(2,0)));
        schedule.addWork(new Work(LocalTime.of(1,0), LocalTime.of(2,0)));
        schedule.addWork(new Work(LocalTime.of(1,0), LocalTime.of(2,0)));
        return schedule;
    }



    /*
     * Описание алгоритма
     * 1. Определяем время движения от распр. центра до первого клиента Тдв.1
     * 2. Определяем суммарное время загрузки заказов данного рейса. Тсум.
     * 3. Определяем время начала загрузки заказов данного рейса
     * Тнач. заг. = Тнач.1окна - Тсум. - Тдв.1
     * Условие
     * Tнач.р.см. < Тнач.заг
     * Нет
     * Устанавливаем время начала загрузки заказов данного рейса
     * Тнач. заг. = Тнач. раб. распрд. центра
     * Да
     * Время начала загрузки заказов данного рейса определено Тнач. заг.
     * Определяем время в пути Т пути
     * Определяем время начала разгрузки заказа у клиента Тнач.разгр = Твыезда из р.ц. + Т пути
     * Определяем время окончания разгрузки заказа у клиента Тнач.разгр = Тнач.разгр. у клиента + Т разгрузки
     * Условие Еще есть заказы ?
     * Нет возвращаем результат
     * Да
     * Определяем время в пути Т пути
     * т. 1 Определяем время начала разгрузки заказа Тнач.разгр = Токонч. разгр. пред. заказа  + Т пути
     * Условие Тнач.разгр < Т нач. окна
     * Нет
     * Тнач.разгр  определено
     * Определяем время окончания разгрузки заказа Токонч.разгр = Тначал. разгр.   + Т разгрузки
     * ДА
     * Тнач.разгр = Т нач. окна
     * Условие Еще есть заказы ?
     * Да возвращаемся в т.1
     * Нет
     * Определяем время возвращения в распрд. центр
     * Твозвр.в р.ц. = Токонч. разгр. пред. заказа  + Т пути
     * Возвращаем результат     * */
}
