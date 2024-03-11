package org.service;

import org.dao.BookmarkGroupDAO;
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

    private final BookmarkGroupDAO bookmarkGroupDAO = new BookmarkGroupDAO();

    public List<BookmarkGroupDTO> getAllGroups() {
        return bookmarkGroupDAO.getAllGroups();
    }

    public void addBookmarkGroup(BookmarkGroupDTO group) {
        bookmarkGroupDAO.addBookmarkGroup(group);
    }

    public void updateBookmarkGroup(BookmarkGroupDTO group) {
        bookmarkGroupDAO.updateBookmarkGroup(group);
    }

    public void deleteBookmarkGroup(int groupId) {
        bookmarkGroupDAO.deleteBookmarkGroup(groupId);
    }

    public BookmarkGroupDTO getBookmarkGroupById(int groupId) {
        return bookmarkGroupDAO.getBookmarkGroupById(groupId);
    }

    public void addWifiToBookmarkGroup(String wifiId, int bookmarkGroupId) {

        bookmarkGroupDAO.addWifiToBookmarkGroup(wifiId, bookmarkGroupId);
    }
}
