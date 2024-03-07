package org.example.service;

import org.example.dao.WifiDAO;
import org.example.dto.WifiDTO;

import java.util.List;

public class WifiService {

    private final WifiDAO wifiDAO;

    public WifiService(WifiDAO wifiDAO) {
        this.wifiDAO = wifiDAO;
    }

    public void insertWifiServiceData(List<WifiDTO> wifiList) {
        try {
            wifiDAO.insertWifiServiceData(wifiList);
        } catch (Exception e) {

            throw new RuntimeException("Error inserting WiFi data", e);
        }
    }
}
