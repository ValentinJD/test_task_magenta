package com.magenta.task.simulation.util;

import com.magenta.task.simulation.dataMock.DistanceData;
import com.magenta.task.simulation.dataMock.ScheduleData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DistanceUtilTest {

    @Test
    void calcForTwoLatitudeAndLongitude() {
        double actualDouble = DistanceUtil.calculate(
                DistanceData.LATITUDE1, DistanceData.LONGITUDE1,
                DistanceData.LATITUDE2, DistanceData.LONGITUDE2);

        long actual = Math.round(actualDouble);

        long expected = Math.round(DistanceData.DISTANCE_BETWEEN_POINTS);

        Assertions.assertEquals(expected, actual);

    }

    @Test
    void calcDistanceOnTwoPoint() {
        double actualDouble = DistanceUtil.calculate(ScheduleData.DC, ScheduleData.POINT1);

        long actual = Math.round(actualDouble);

        long expected = 10;

        Assertions.assertEquals(expected, actual);

    }
}