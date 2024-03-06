package org.example;

import java.sql.*;
import java.util.List;

public class DatabaseConnection {
    private static String url = "jdbc:mariadb://localhost:3306/test";
    private static String dbUserID = "kyoominlee@localhost";
    static String dbPassword = "";

    static {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void insertWifiServiceData(List<Wifi> wifiList) {
        try (Connection connection = DriverManager.getConnection(url, dbUserID, dbPassword)) {
            System.out.println("연결 완료");
            String insertSql = "INSERT INTO wifi_service (X_SWIFI_MGR_NO, X_SWIFI_WRDOFC, X_SWIFI_MAIN_NM, " +
                    "X_SWIFI_ADRES1, X_SWIFI_ADRES2, X_SWIFI_INSTL_FLOOR, X_SWIFI_INSTL_TY, X_SWIFI_INSTL_MBY, " +
                    "X_SWIFI_SVC_SE, X_SWIFI_CMCWR, X_SWIFI_CNSTC_YEAR, X_SWIFI_INOUT_DOOR, X_SWIFI_REMARS3, LAT, LNT, WORK_DTTM) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            try (PreparedStatement preparedStatement = connection.prepareStatement(insertSql)) {
                for (Wifi wifi : wifiList) {
                    preparedStatement.setString(1, wifi.getManagerNumber());
                    preparedStatement.setString(2, wifi.getJurisdiction());
                    preparedStatement.setString(3, wifi.getMainName());
                    preparedStatement.setString(4, wifi.getAddress1());
                    preparedStatement.setString(5, wifi.getAddress2());
                    preparedStatement.setString(6, wifi.getInstallationFloor());
                    preparedStatement.setString(7, wifi.getInstallationType());
                    preparedStatement.setString(8, wifi.getInstallationBy());
                    preparedStatement.setString(9, wifi.getServiceType());
                    preparedStatement.setString(10, wifi.getCommunicationCarrier());
                    preparedStatement.setInt(11, wifi.getConstructionYear());
                    preparedStatement.setString(12, wifi.getInOutDoor());
                    preparedStatement.setString(13, wifi.getRemarks());
                    preparedStatement.setBigDecimal(14, wifi.getLatitude());
                    preparedStatement.setBigDecimal(15, wifi.getLongitude());
                    preparedStatement.setTimestamp(16, wifi.getTimestamp());

                    preparedStatement.addBatch();
                }

                preparedStatement.executeBatch();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}