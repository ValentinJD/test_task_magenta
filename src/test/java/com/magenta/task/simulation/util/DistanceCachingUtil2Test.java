package com.magenta.task.simulation.util;

import com.magenta.task.simulation.dataMock.ScheduleData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;


@SpringBootTest
public class DistanceCachingUtil2Test {

    @Autowired
    DistanceCachingUtil2 distanceUtil2;

    @Test
    void calcCacheable() {
        double calculateDouble = distanceUtil2.calculate(ScheduleData.DC, ScheduleData.POINT1);
        double doubleFromCache = distanceUtil2.calculate(ScheduleData.DC, ScheduleData.POINT1);

        Assertions.assertEquals(calculateDouble, doubleFromCache);
    }
}
