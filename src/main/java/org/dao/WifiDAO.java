package org.dao;

import org.dto.WifiDTO;
import org.util.DatabaseConnection;
import org.util.DistanceCalculator;

import java.sql.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class WifiDAO {

    private static final Logger LOGGER = Logger.getLogger(WifiDAO.class.getName());

    public List<WifiDTO> getWifiDataWithDistance(double userLatitude, double userLongitude, int page, int pageSize) {
        List<WifiDTO> wifiList = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "SELECT * FROM wifi_service";
            PreparedStatement stmt = conn.prepareStatement(sql);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                WifiDTO wifi = new WifiDTO(
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
                        rs.getString("X_SWIFI_CNSTC_YEAR"),
                        rs.getString("X_SWIFI_INOUT_DOOR"),
                        rs.getString("X_SWIFI_REMARS3"),
                        rs.getBigDecimal("LAT"),
                        rs.getBigDecimal("LNT"),
                        rs.getTimestamp("WORK_DTTM"),
                        0.0
                );
                double distance = DistanceCalculator.calculateHaversineDistance(
                        userLatitude, userLongitude, wifi.getLatitude().doubleValue(), wifi.getLongitude().doubleValue());
                wifi.setDistance(distance);

                wifiList.add(wifi);
            }

            wifiList = wifiList.stream()
                    .sorted(Comparator.comparingDouble(WifiDTO::getDistance))
                    .skip((long) (page - 1) * pageSize)
                    .limit(pageSize)
                    .collect(Collectors.toList());

        } catch (SQLException e) {
            LOGGER.severe("SQL Exception: " + e.getMessage());
        }
        return wifiList;
    }

    public WifiDTO getWifiDetailsByMgrNo(String mgrNumber) {
        WifiDTO wifi = null;

        String sql = "SELECT * FROM wifi_service WHERE X_SWIFI_MGR_NO = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, mgrNumber);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                wifi = new WifiDTO(
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
                        rs.getString("X_SWIFI_CNSTC_YEAR"),
                        rs.getString("X_SWIFI_INOUT_DOOR"),
                        rs.getString("X_SWIFI_REMARS3"),
                        rs.getBigDecimal("LAT"),
                        rs.getBigDecimal("LNT"),
                        rs.getTimestamp("WORK_DTTM"),
                        0.0
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return wifi;
    }

    public void insertWifiServiceData(List<WifiDTO> wifiList) {
        try (Connection connection = DatabaseConnection.getConnection()) {
            String insertSql = "INSERT INTO wifi_service (X_SWIFI_MGR_NO, X_SWIFI_WRDOFC, X_SWIFI_MAIN_NM, " +
                    "X_SWIFI_ADRES1, X_SWIFI_ADRES2, X_SWIFI_INSTL_FLOOR, X_SWIFI_INSTL_TY, X_SWIFI_INSTL_MBY, " +
                    "X_SWIFI_SVC_SE, X_SWIFI_CMCWR, X_SWIFI_CNSTC_YEAR, X_SWIFI_INOUT_DOOR, X_SWIFI_REMARS3, LAT, LNT, WORK_DTTM) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            try (PreparedStatement preparedStatement = connection.prepareStatement(insertSql)) {
                for (WifiDTO wifi : wifiList) {
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
                    preparedStatement.setString(11, wifi.getConstructionYear());
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
