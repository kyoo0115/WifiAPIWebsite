package org.service;

import org.dao.LocationHistoryDAO;
import org.dto.BookmarkGroupDTO;
import org.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class BookmarkGroupService {

    private static final String SELECT_ALL_BOOKMARK_GROUPS = "SELECT id, name, display_order, added_time, edited_time FROM bookmark_group";
    private static final String INSERT_BOOKMARK_GROUP = "INSERT INTO bookmark_group (name, display_order) VALUES (?, ?)";
    private static final String UPDATE_BOOKMARK_GROUP = "UPDATE bookmark_group SET name = ?, display_order = ? WHERE id = ?";
    private static final String SELECT_BOOKMARK_GROUP_BY_ID = "SELECT id, name, display_order, added_time, edited_time FROM bookmark_group WHERE id = ?";
    private static final Logger LOGGER = Logger.getLogger(LocationHistoryDAO.class.getName());
    public BookmarkGroupService() {

    }

    public List<BookmarkGroupDTO> getAllGroups() {
        List<BookmarkGroupDTO> groups = new ArrayList<>();

        try (Connection connection = DatabaseConnection.getConnection()) {

        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_BOOKMARK_GROUPS); {

                ResultSet rs = preparedStatement.executeQuery();

                while (rs.next()) {
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    int displayOrder = rs.getInt("display_order");
                    Timestamp addedTime = rs.getTimestamp("added_time");
                    Timestamp editedTime = rs.getTimestamp("edited_time");

                    BookmarkGroupDTO group = new BookmarkGroupDTO(name, displayOrder);
                    group.setId(id);
                    group.setAddedTime(addedTime);
                    group.setEditedTime(editedTime);
                    groups.add(group);
                }
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

            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating bookmark group failed, no rows affected.");
            }

        } catch (SQLException e) {
            LOGGER.severe("SQL Exception: " + e.getMessage());
        }
    }

    public void addWifiToBookmarkGroup(String wifiId, int bookmarkGroupId) {
        String INSERT_RELATIONSHIP = "INSERT INTO wifi_bookmark (bookmark_group_id, wifi_id) VALUES (?, ?)";

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

    public void updateBookmarkGroup(BookmarkGroupDTO group) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_BOOKMARK_GROUP)) {

            preparedStatement.setString(1, group.getName());
            preparedStatement.setInt(2, group.getDisplayOrder());
            preparedStatement.setInt(3, group.getId());

            System.out.println("Updating group with ID: " + group.getId() + ", Name: " + group.getName() + ", Display Order: " + group.getDisplayOrder());
            int affectedRows = preparedStatement.executeUpdate();
            System.out.println("Affected rows: " + affectedRows);
            if (affectedRows == 0) {
                throw new SQLException("Updating bookmark group failed, no rows affected.");
            }

        } catch (SQLException e) {
            LOGGER.severe("SQL Exception: " + e.getMessage());
        }
    }

    public void deleteBookmarkGroup(int groupId) {

        String deleteReferencesSql = "DELETE FROM wifi_bookmark WHERE bookmark_group_id = ?";
        String deleteGroupSql = "DELETE FROM bookmark_group WHERE id = ?";

        try (Connection connection = DatabaseConnection.getConnection();

             PreparedStatement deleteRefsStmt = connection.prepareStatement(deleteReferencesSql);
             PreparedStatement deleteGroupStmt = connection.prepareStatement(deleteGroupSql)) {

            deleteRefsStmt.setInt(1, groupId);
            deleteRefsStmt.executeUpdate();

            deleteGroupStmt.setInt(1, groupId);
            int affectedRows = deleteGroupStmt.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Deleting bookmark group failed, no rows affected.");
            }

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
}
