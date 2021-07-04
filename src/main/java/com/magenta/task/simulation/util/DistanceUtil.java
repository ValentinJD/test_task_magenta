package com.magenta.task.simulation.util;

import com.magenta.task.simulation.model.PointInMap;

public class DistanceUtil {

    private static final double RADIUS_EARTH = 6371.0086;


    // Широта(latitude) и долгота (longitude) выражена в десятичных градуса

    //https://congyuzhou.medium.com/%D1%80%D0%B0%D1%81%D1%81%D1%82%D0%BE%D1%8F%D0%BD%D0%B8%D0%B5-%D0%BC%D0%B5%D0%B6%D0%B4%D1%83-%D0%B4%D0%B2%D1%83%D0%BC%D1%8F-%D1%82%D0%BE%D1%87%D0%BA%D0%B0%D0%BC%D0%B8-%D0%BD%D0%B0-%D0%BF%D0%BE%D0%B2%D0%B5%D1%80%D1%85%D0%BD%D0%BE%D1%81%D1%82%D0%B8-%D0%B7%D0%B5%D0%BC%D0%BB%D0%B8-a398352bfbde
    public static double calculate(double latitude1, double longitude1, double latitude2, double longitude2) {

        return 2 * RADIUS_EARTH * Math.asin(
                Math.sqrt(
                        Math.pow(Math.sin((Math.toRadians(latitude2) - Math.toRadians(latitude1)) / 2), 2)
                                + (Math.cos(Math.toRadians(latitude1)) * Math.cos(Math.toRadians(latitude2))
                                * Math.pow(Math.sin((Math.toRadians(longitude2) - Math.toRadians(longitude1)) / 2), 2)
                        )
                ));
    }

    public static double calculate(PointInMap point1, PointInMap point2) {
        return calculate(point1.getLatitude(), point1.getLongitude(), point2.getLatitude(), point2.getLongitude());
    }


}
