package org.example;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ApiResponse {
    @SerializedName("TbPublicWifiInfo")
    private WifiInfo wifiInfo;

    public List<Wifi> getWifiList() {
        return wifiInfo.row;
    }

    private static class WifiInfo {
        @SerializedName("row")
        private List<Wifi> row;
    }
}
