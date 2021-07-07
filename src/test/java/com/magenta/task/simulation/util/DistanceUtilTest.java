package com.magenta.task.simulation.util;

import com.magenta.task.simulation.dataMock.DistanceData;
import com.magenta.task.simulation.dataMock.ScheduleData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class DistanceUtilTest {

    @Test
    void calcDistanceOnTwoPoint() {
        double actualDouble = DistanceUtil.calculate(ScheduleData.DC, ScheduleData.POINT1);

        long actual = Math.round(actualDouble);

        long expected = ScheduleData.DISTANCE_DC_POINT1;

        Assertions.assertEquals(expected, actual);

    }
}