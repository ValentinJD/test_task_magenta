package com.magenta.task.simulation.util;

import com.magenta.task.simulation.dataMock.ScheduleData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;


@SpringBootTest
public class DistanceCachingUtilTest {

    @Autowired
    DistanceCachingUtil distanceUtil2;

    @Autowired
    CacheManager cacheManager;

    @Test
    void calcCacheable() {

        distanceUtil2.calculate(ScheduleData.DC, ScheduleData.POINT1);

        int countCallCalculateGetBefore = distanceUtil2.getCountCallGet();

        distanceUtil2.calculate(ScheduleData.DC, ScheduleData.POINT1);
        distanceUtil2.calculate(ScheduleData.DC, ScheduleData.POINT1);


        int countCallCalculateGetAfter = distanceUtil2.getCountCallGet();

        Assertions.assertEquals(countCallCalculateGetBefore, countCallCalculateGetAfter);

    }
}
