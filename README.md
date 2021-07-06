# Тестовое задание
## 1.Симуляция.
Необходимо реализовать алгоритм, который выбирает оптимальное время начала погрузки с учетом временных окон доступности заказов и часов работы распределительного центра, а также вычисляет времена начала и завершения обслуживания каждого заказа в рейсе и время возвращения машины в распределительный центр.

Реализацией алгоритма задачи "Симуляция" является класс SimulationAlgorithmSequential.

Тесты данного класса находятся в классе AlgorithmSequentialTest

Данные для тестирования вычислений расстояний см. DistanceData.

Данные для тестирования вычисления расписания см. ScheduleData

Методы для расчета расстояния см. DistanceUtil

Методы расчета расписания CalcTimeUtil

Запуск тестов mvn clean test

Визуальное представление тестовых данных resources/static/timeSchedule.jpg

Блок схема алгоритма resources/static/schemeAlgorithm.jpg

Также можно запустить метод main() класса TaskApplication посмотреть результаты работы 
в консоли.

