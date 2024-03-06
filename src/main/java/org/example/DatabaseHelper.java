package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatabaseHelper {
    private static final String DB_URL = "jdbc:mariadb://localhost:3306/test";
    private static final String USER = "kyoominlee@localhost";
    private static final String PASS = "";

    public static List<Wifi> getWifiDataWithDistance(double userLatitude, double userLongitude) {
        List<Wifi> wifiList = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement stmt = conn.prepareStatement("SELECT *, LAT, LNT FROM wifi_service")) {

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Wifi wifi = new Wifi(
                        rs.getString("X_SWIFI_MGR_NO"),
                        rs.getString("X_SWIFI_WRDOFC"),
                        rs.getString("X_SWIFI_MAIN_NM"),
                        rs.getString("X_SWIFI_ADRES1"),
                        rs.getString("X_SWIFI_ADRES2"),
                        rs.getString("X_SWIFI_INSTL_FLOOR"),
                        rs.getString("X_SWIFI_INSTL_TY"),
                        rs.getString("X_SWIFI_INSTL_MBY"),
                        rs.getString("X_SWIFI_SVC_SE"),
                        rs.getString("X_SWIFI_CMCWR"),
                        rs.getInt("X_SWIFI_CNSTC_YEAR"),
                        rs.getString("X_SWIFI_INOUT_DOOR"),
                        rs.getString("X_SWIFI_REMARS3"),
                        rs.getBigDecimal("LAT"),
                        rs.getBigDecimal("LNT"),
                        rs.getTimestamp("WORK_DTTM"),
                        0.0
                );

                double distance = DistanceCalculator.calculateHaversineDistance(userLatitude, userLongitude, wifi.getLatitude().doubleValue(), wifi.getLongitude().doubleValue());
                wifi.setDistance(distance);

                wifiList.add(wifi);
            }

            wifiList.sort(Comparator.comparingDouble(Wifi::getDistance));

        } catch (SQLException e) {
            Logger.getLogger(DatabaseHelper.class.getName()).log(Level.SEVERE, null, e);
        }
        return wifiList;
    }

}
