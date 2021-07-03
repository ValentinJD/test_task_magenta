package com.magenta.task.simulation.util;

import com.magenta.task.simulation.model.PointInMap;

public class DistanceUtil {

    private static final double RADIUS_EARTH = 6371.0086;

    /**
     * input latitude and longitude in degree
     */
    public static double calculate(double latitude1, double longitude1, double latitude2, double longitude2) {

        double d = 2 * RADIUS_EARTH * Math.asin(
                Math.sqrt(
                        Math.pow(Math.sin((Math.toRadians(latitude2) - Math.toRadians(latitude1)) / 2), 2)
                                + (Math.cos(Math.toRadians(latitude1)) * Math.cos(Math.toRadians(latitude2))
                                * Math.pow(Math.sin((Math.toRadians(longitude2) - Math.toRadians(longitude1)) / 2), 2)
                        )
                ));

        return d;
    }

    public static double calculate(PointInMap point1, PointInMap point2) {
        return calculate(point1.getLatitude(), point1.getLongitude(), point2.getLatitude(), point2.getLongitude());
    }



}
