package org.example.dto;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import org.example.dto.WifiDTO;

import java.util.List;

@Data
public class ApiResponse {
    @SerializedName("TbPublicWifiInfo")
    private WifiInfo wifiInfo;

    public List<WifiDTO> getWifiList() {
        return wifiInfo.row;
    }

    private static class WifiInfo {
        @SerializedName("row")
        private List<WifiDTO> row;
    }
}
