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
import java.util.logging.Logger;


public class DistanceUtil {

    public static double calculate(PointInMap point1, PointInMap point2) {
        final Logger logger = Logger.getLogger(DistanceUtil.class.getName());
        logger.info("calculate ");
        return getDistanceInKiloMeters(point1, point2);
    }

    public static double getDistanceInKiloMeters(PointInMap point1, PointInMap point2) {

        String url = URLBuilder.build(point1, point2);

        String json = getJson(url);

        return getDistanceInKiloMeters(json);
    }

    public static String getJson(final String url) {

        StringBuilder response = new StringBuilder();

        try {
            URL obj = new URL(url);

            HttpURLConnection connection = (HttpURLConnection) obj.openConnection();
            connection.setRequestMethod("GET");

            try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                String inputLine;

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
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
}
