package org.example;

import java.io.IOException;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        try {
            List<Wifi> wifiServiceList = OpenAPI.fetchDataFromOpenAPI();

            DatabaseConnection.insertWifiServiceData(wifiServiceList);

            System.out.println("Data inserted successfully.");
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error fetching data from OpenAPI: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error: " + e.getMessage());
        }
    }
}
