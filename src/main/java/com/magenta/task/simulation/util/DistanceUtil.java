package com.magenta.task.simulation.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.magenta.task.simulation.exception.InvalidQueryException;
import com.magenta.task.simulation.model.PointInMap;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class DistanceUtil {

    private static final double RADIUS_EARTH = 6371.0086;


    // Широта(latitude) и долгота (longitude) выражена в десятичных градуса
    // вычисление расстояния по прямой линии
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
        return getDistanceInKiloMeters(point1, point2);
    }

    public static double getDistanceInKiloMeters(PointInMap point1, PointInMap point2) {

        String url = URLBuilder.build(point1, point2);

        String json = getJson(url);

        return getDistanceInKiloMeters(json);


    }


    public static String getJson(final String url) {

        StringBuffer response = new StringBuffer();

        try {
            URL obj = new URL(url);

            HttpURLConnection connection = (HttpURLConnection) obj.openConnection();
            connection.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            try {
                String inputLine;

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
            } finally {
                in.close();
            }
        } catch (Exception e) {

            e.printStackTrace();

            throw new InvalidQueryException("InvalidQuery");
        }

        return response.toString();
    }

    public static JsonNode getJsonNode(final String json) throws IOException, JSONException {

        final ObjectMapper mapper = new ObjectMapper();

        final ObjectNode node = mapper.readValue(json, ObjectNode.class);

        return node.get("routes");
    }


    public static Double getDistanceInKiloMeters(String json) {

        String distance;

        try {
            distance = getJsonNode(json).get(0).get("distance").asText();
        } catch (IOException e) {

            e.printStackTrace();

            throw new InvalidQueryException("InvalidQuery");
        }

        return Double.parseDouble(distance) / 1000;
    }

    public static Integer getDurationInMinutes(String json) {

        String duration;

        try {
            duration = getJsonNode(json).get(0).get("duration").asText();
        } catch (IOException e) {
            e.printStackTrace();

            throw new InvalidQueryException("InvalidQuery");
        }

        return Math.round(Float.parseFloat(duration)) / 60;
    }


}
