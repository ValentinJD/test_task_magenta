package com.magenta.task.simulation.util;

import com.magenta.task.simulation.model.PointInMap;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
public class DistanceCachingUtil {
    private int countCallGet = 0;

    @Cacheable("distance")
    public double calculate(PointInMap point1, PointInMap point2) {
        countCallGet++;
        return DistanceUtil.getDistanceInKiloMeters(point1, point2);
    }

    public int getCountCallGet() {
        return countCallGet;
    }
}
