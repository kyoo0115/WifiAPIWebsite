package org.dao;

import org.dto.BookmarkGroupDTO;
import org.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class BookmarkGroupDAO {

    private static final String SELECT_ALL_BOOKMARK_GROUPS = "SELECT id, name, display_order, added_time, edited_time FROM bookmark_group";
    private static final String INSERT_BOOKMARK_GROUP = "INSERT INTO bookmark_group (name, display_order) VALUES (?, ?)";
    private static final String UPDATE_BOOKMARK_GROUP = "UPDATE bookmark_group SET name = ?, display_order = ? WHERE id = ?";
    private static final String DELETE_REFERENCES_SQL = "DELETE FROM wifi_bookmark WHERE bookmark_group_id = ?";
    private static final String DELETE_GROUP_SQL = "DELETE FROM bookmark_group WHERE id = ?";
    private static final String SELECT_BOOKMARK_GROUP_BY_ID = "SELECT id, name, display_order, added_time, edited_time FROM bookmark_group WHERE id = ?";
    private static final String INSERT_RELATIONSHIP = "INSERT INTO wifi_bookmark (bookmark_group_id, wifi_id) VALUES (?, ?)";

    private static final Logger LOGGER = Logger.getLogger(BookmarkGroupDAO.class.getName());

    public List<BookmarkGroupDTO> getAllGroups() {
        List<BookmarkGroupDTO> groups = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_BOOKMARK_GROUPS);
             ResultSet rs = preparedStatement.executeQuery()) {

            while (rs.next()) {
                BookmarkGroupDTO group = new BookmarkGroupDTO(rs.getString("name"), rs.getInt("display_order"));
                group.setId(rs.getInt("id"));
                group.setAddedTime(rs.getTimestamp("added_time"));
                group.setEditedTime(rs.getTimestamp("edited_time"));
                groups.add(group);
            }
        } catch (SQLException e) {
            LOGGER.severe("SQL Exception: " + e.getMessage());
        }
        return groups;
    }

    public void addBookmarkGroup(BookmarkGroupDTO group) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_BOOKMARK_GROUP)) {

            preparedStatement.setString(1, group.getName());
            preparedStatement.setInt(2, group.getDisplayOrder());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.severe("SQL Exception: " + e.getMessage());
        }
    }

    public void updateBookmarkGroup(BookmarkGroupDTO group) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_BOOKMARK_GROUP)) {

            preparedStatement.setString(1, group.getName());
            preparedStatement.setInt(2, group.getDisplayOrder());
            preparedStatement.setInt(3, group.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.severe("SQL Exception: " + e.getMessage());
        }
    }

    public void deleteBookmarkGroup(int groupId) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement deleteRefsStmt = connection.prepareStatement(DELETE_REFERENCES_SQL);
             PreparedStatement deleteGroupStmt = connection.prepareStatement(DELETE_GROUP_SQL)) {

            deleteRefsStmt.setInt(1, groupId);
            deleteRefsStmt.executeUpdate();

            deleteGroupStmt.setInt(1, groupId);
            deleteGroupStmt.executeUpdate();
        } catch (SQLException e) {
            LOGGER.severe("SQL Exception: " + e.getMessage());
        }
    }

    public BookmarkGroupDTO getBookmarkGroupById(int groupId) {
        BookmarkGroupDTO group = null;
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BOOKMARK_GROUP_BY_ID)) {

            preparedStatement.setInt(1, groupId);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                group = new BookmarkGroupDTO(rs.getString("name"), rs.getInt("display_order"));
                group.setId(rs.getInt("id"));
                group.setAddedTime(rs.getTimestamp("added_time"));
                group.setEditedTime(rs.getTimestamp("edited_time"));
            }
        } catch (SQLException e) {
            LOGGER.severe("SQL Exception: " + e.getMessage());
        }
        return group;
    }

    public void addWifiToBookmarkGroup(String wifiId, int bookmarkGroupId) {

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_RELATIONSHIP)) {

            preparedStatement.setInt(1, bookmarkGroupId);
            preparedStatement.setString(2, wifiId);

            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Inserting WiFi to bookmark group failed, no rows affected.");
            }

        } catch (SQLException e) {
            LOGGER.severe("SQL Exception: " + e.getMessage());
        }
    }
}
