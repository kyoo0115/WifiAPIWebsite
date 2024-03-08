package org.example.dao;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.example.dto.ApiResponse;
import org.example.dto.WifiDTO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import static org.example.util.Constants.*;

public class OpenAPIDAO {
    public List<WifiDTO> fetchDataFromOpenAPI() throws IOException {
        List<WifiDTO> allWifiData = new ArrayList<>();
        final int pageSize = 1000;
        int totalCount = fetchTotalCount();
        int totalPages = totalCount / pageSize + (totalCount % pageSize == 0 ? 0 : 1);

        for (int i = 0; i < totalPages; i++) {
            int startIndex = i * pageSize;
            int endIndex = (i + 1) * pageSize - 1;
            String urlString = String.format(BASE_URL + "/%s/json/%s/%d/%d",
                    API_KEY, API_SERVICE_NAME, startIndex, endIndex);

            String jsonResponse = makeHttpRequest(urlString);

            List<WifiDTO> wifiList = parseJsonResponse(jsonResponse);
            allWifiData.addAll(wifiList);
        }

        return allWifiData;
    }

    private List<WifiDTO> parseJsonResponse(String jsonResponse) {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd HH:mm:ss.S")
                .create();
        ApiResponse response = gson.fromJson(jsonResponse, ApiResponse.class);
        return response.getWifiList();
    }

    private String makeHttpRequest(String urlString) throws IOException {
        StringBuilder response = new StringBuilder();
        URL url = new URL(urlString);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");

        try (BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8))) {
            String line;
            while ((line = rd.readLine()) != null) {
                response.append(line);
            }
        } finally {
            conn.disconnect();
        }

        return response.toString();
    }

    public static int fetchTotalCount() {
        String baseUrl = String.format("http://openapi.seoul.go.kr:8088/%s/json/TbPublicWifiInfo/1/1", API_KEY);
        try {
            String jsonResponse = new OpenAPIDAO().makeHttpRequest(baseUrl);
            JsonObject result = JsonParser.parseString(jsonResponse).getAsJsonObject();
            JsonObject data = result.getAsJsonObject(API_SERVICE_NAME);
            return data.get("list_total_count").getAsInt();
        } catch (IOException e) {
            System.err.println("Error fetching total count: " + e.getMessage());
            return -1;
        }
    }
}
