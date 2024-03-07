package org.example.dto;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class WifiDTO {
    @SerializedName("X_SWIFI_MGR_NO")
    private String managerNumber;

    @SerializedName("X_SWIFI_WRDOFC")
    private String jurisdiction;

    @SerializedName("X_SWIFI_MAIN_NM")
    private String mainName;

    @SerializedName("X_SWIFI_ADRES1")
    private String address1;

    @SerializedName("X_SWIFI_ADRES2")
    private String address2;

    @SerializedName("X_SWIFI_INSTL_FLOOR")
    private String installationFloor;

    @SerializedName("X_SWIFI_INSTL_TY")
    private String installationType;

    @SerializedName("X_SWIFI_INSTL_MBY")
    private String installationBy;

    @SerializedName("X_SWIFI_SVC_SE")
    private String serviceType;

    @SerializedName("X_SWIFI_CMCWR")
    private String communicationCarrier;

    @SerializedName("X_SWIFI_CNSTC_YEAR")
    private int constructionYear;

    @SerializedName("X_SWIFI_INOUT_DOOR")
    private String inOutDoor;

    @SerializedName("X_SWIFI_REMARS3")
    private String remarks;

    @SerializedName("LAT")
    private BigDecimal latitude;

    @SerializedName("LNT")
    private BigDecimal longitude;

    @SerializedName("WORK_DTTM")
    private java.sql.Timestamp timestamp;

    private Double distance;
}