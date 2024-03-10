package org.service;

import org.dto.BookmarkGroupDTO;
import org.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class BookmarkGroupService {

    private static final String SELECT_ALL_BOOKMARK_GROUPS = "SELECT id, name, display_order, added_time, edited_time, remarks FROM bookmark_group";
    private static final String INSERT_BOOKMARK_GROUP = "INSERT INTO bookmark_group (name, display_order, remarks) VALUES (?, ?, ?)";
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
                    String remarks = rs.getString("remarks");

                    BookmarkGroupDTO group = new BookmarkGroupDTO(name, displayOrder, remarks);
                    group.setId(id);
                    group.setAddedTime(addedTime);
                    group.setEditedTime(editedTime);
                    groups.add(group);
                }


            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return groups;
    }

    public void addBookmarkGroup(BookmarkGroupDTO group) {

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_BOOKMARK_GROUP)) {

            preparedStatement.setString(1, group.getName());
            preparedStatement.setInt(2, group.getDisplayOrder());
            preparedStatement.setString(3, group.getRemarks());

            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating bookmark group failed, no rows affected.");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
