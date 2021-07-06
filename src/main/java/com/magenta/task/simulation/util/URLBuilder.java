package com.magenta.task.simulation.util;

import com.magenta.task.simulation.model.PointInMap;

public class URLBuilder {

    // String url = http://router.project-osrm.org/route/v1/driving/53.232244,50.250508;53.198343,50.111600";

    public static String build(String latitude1, String longitude1, String latitude2, String longitude2) {

        String baseUrl = "http://router.project-osrm.org/route/v1/driving/";

        return baseUrl + longitude1 + ',' + latitude1 + ';' + longitude2 + ',' + latitude2;
    }

    public static String build(PointInMap point1, PointInMap point2) {

        String lat1 = Double.toString(point1.getLatitude());
        String lon1 = Double.toString(point1.getLongitude());
        String lat2 = Double.toString(point2.getLatitude());
        String lon2 = Double.toString(point2.getLongitude());

        return build(lat1, lon1, lat2, lon2);
    }
}
