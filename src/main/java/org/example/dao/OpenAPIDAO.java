package org.example.dao;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.example.dto.ApiResponse;
import org.example.dto.WifiDTO;
import org.example.util.Constants;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import static org.example.util.Constants.*;

public class OpenAPIDAO {
    public List<WifiDTO> fetchDataFromOpenAPI() throws IOException {

        String urlBuilder = "http://openapi.seoul.go.kr:8088/" + API_KEY +
                "/json/" + API_SERVICE_NAME +
                "/" + API_START_INDEX +
                "/" + API_END_INDEX;

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

    private List<WifiDTO> parseJsonResponse(String jsonResponse) {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd HH:mm:ss.S")
                .create();
        ApiResponse response = gson.fromJson(jsonResponse, ApiResponse.class);
        return response.getWifiList();
    }
}
