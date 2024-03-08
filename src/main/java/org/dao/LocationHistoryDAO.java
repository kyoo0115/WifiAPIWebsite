package org.dao;

import org.dto.LocationHistoryDTO;
import org.util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class LocationHistoryDAO {
    private static final Logger LOGGER = Logger.getLogger(LocationHistoryDAO.class.getName());

    public List<LocationHistoryDTO> fetchLocationHistory() {
        List<LocationHistoryDTO> historyList = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection()) {

            String sql = "SELECT * FROM location_history";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                LocationHistoryDTO history = new LocationHistoryDTO(

                        rs.getInt("id"),
                        rs.getBigDecimal("latitude"),
                        rs.getBigDecimal("longitude"),
                        rs.getTimestamp("timestamp")
                );
                historyList.add(history);
            }
        } catch (SQLException e) {
            LOGGER.severe("SQL Exception: " + e.getMessage());
        }
        return historyList;
    }

    public void recordLocation(double latitude, double longitude) throws SQLException {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "INSERT INTO location_history (latitude, longitude) VALUES (?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setDouble(1, latitude);
                stmt.setDouble(2, longitude);
                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            LOGGER.severe("SQL Exception: " + e.getMessage());
        }
    }

    public void deleteRecord(int id) throws SQLException {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "DELETE FROM location_history WHERE id = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, id);
                int affectedRows = stmt.executeUpdate();
                if (affectedRows == 0) {
                    throw new SQLException("Deleting record failed, no rows affected.");
                }
            }
        } catch (SQLException e) {
            LOGGER.severe("SQL Exception: " + e.getMessage());
        }
    }
}
