package com.magenta.task.simulation;

import com.magenta.task.simulation.dataMock.ScheduleData;
import com.magenta.task.simulation.model.Schedule;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AlgorithmSequentialTest {

    @Autowired
    SimulationAlgorithm algorithmSequential;

    @Test
    void simulate() {
        Schedule actual = algorithmSequential.simulate(ScheduleData.getOrders(), ScheduleData.UAZ_452,
                ScheduleData.DISTRIBUTION_CENTER);

        Schedule expected = ScheduleData.SCHEDULE;

        Assertions.assertIterableEquals(expected.getWorkList(), actual.getWorkList());
        Assertions.assertEquals(expected.getTimeGoToOutDC(), actual.getTimeGoToOutDC());
        Assertions.assertEquals(expected.getTimeToReturnInDC(), actual.getTimeToReturnInDC());
    }
}