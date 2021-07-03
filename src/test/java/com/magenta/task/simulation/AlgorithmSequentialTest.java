package com.magenta.task.simulation;

import com.magenta.task.simulation.dataMock.ScheduleData;
import com.magenta.task.simulation.model.Schedule;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalTime;

@SpringBootTest
class AlgorithmSequentialTest {

    @Autowired
    SimulationAlgorithm algorithmSequential;

    @Test
    void simulate() {
        Schedule actual = algorithmSequential.simulate(ScheduleData.getOrders(), ScheduleData.UAZ_452,
                LocalTime.of(8,0), LocalTime.of(22,0));

        Schedule expected = ScheduleData.SCHEDULE;

        Assertions.assertIterableEquals(expected.getWorkList(), actual.getWorkList());
        Assertions.assertEquals(expected.getTimeGoToOutDC(), actual.getTimeGoToOutDC());
        Assertions.assertEquals(expected.getTimeToReturnInDC(), actual.getTimeToReturnInDC());
    }
}