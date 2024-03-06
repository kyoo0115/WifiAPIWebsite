package org.example;

import com.google.gson.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class OpenAPI {
    public static List<Wifi> fetchDataFromOpenAPI() throws IOException {

        String apiKey = "4e5a42726c6b796f35335079446466";
        String service = "TbPublicWifiInfo";
        int startIndex = 3000;
        int endIndex = 3999;

        String urlBuilder = "http://openapi.seoul.go.kr:8088/" + apiKey +
                "/json/" + service +
                "/" + startIndex +
                "/" + endIndex;

        URL url = new URL(urlBuilder);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");

        try (BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = rd.readLine()) != null) {
                response.append(line);
            }

            System.out.println("Raw JSON Response: " + response);

            return parseJsonResponse(response.toString());
        } finally {
            conn.disconnect();
        }
    }

    private static List<Wifi> parseJsonResponse(String jsonResponse) {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd HH:mm:ss.S")
                .create();
        ApiResponse response = gson.fromJson(jsonResponse, ApiResponse.class);
        return response.getWifiList();
    }
}
