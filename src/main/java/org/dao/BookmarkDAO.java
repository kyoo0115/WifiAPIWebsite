package org.dao;

import org.dto.WifiBookmarkDTO;
import org.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class BookmarkDAO {
    private static final String DELETE_BOOKMARK_SQL = "DELETE FROM wifi_bookmark WHERE id = ?";
    private static final String GET_ALL_BOOKMARK_SQL = "SELECT wb.id, wb.bookmark_group_id, wb.wifi_id, ws.X_SWIFI_MAIN_NM AS wifi_name, wb.registration_time, bg.name AS bookmark_group_name " +
            "FROM wifi_bookmark wb " +
            "JOIN wifi_service ws ON wb.wifi_id = ws.X_SWIFI_MGR_NO " +
            "JOIN bookmark_group bg ON wb.bookmark_group_id = bg.id " +
            "ORDER BY wb.bookmark_group_id";

    private static final Logger LOGGER = Logger.getLogger(BookmarkGroupDAO.class.getName());

    public List<WifiBookmarkDTO> getAllBookmarks() {
        List<WifiBookmarkDTO> bookmarks = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(GET_ALL_BOOKMARK_SQL)) {

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                WifiBookmarkDTO bookmark = new WifiBookmarkDTO(
                        rs.getInt("id"),
                        rs.getInt("bookmark_group_id"),
                        rs.getString("wifi_id"),
                        rs.getString("wifi_name"),
                        rs.getTimestamp("registration_time"),
                        rs.getString("bookmark_group_name")
                );
                bookmarks.add(bookmark);
            }
        } catch (SQLException e) {
            LOGGER.severe("SQL Exception: " + e.getMessage());
        }
        return bookmarks;
    }

    public void deleteBookmark(int bookmarkId) {


        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BOOKMARK_SQL)) {

            preparedStatement.setInt(1, bookmarkId);

            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Deleting bookmark failed, no rows affected.");
            }

        } catch (SQLException e) {
            LOGGER.severe("SQL Exception: " + e.getMessage());
        }
    }
}
