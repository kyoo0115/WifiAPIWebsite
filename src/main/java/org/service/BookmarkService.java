package org.service;

import org.dao.BookmarkDAO;
import org.dto.WifiBookmarkDTO;

import java.util.List;

public class BookmarkService {

    private final BookmarkDAO bookmarkDAO = new BookmarkDAO();

    public List<WifiBookmarkDTO> getAllBookmarks() {
        return bookmarkDAO.getAllBookmarks();
    }

    public void deleteBookmark(int bookmarkId) {
        bookmarkDAO.deleteBookmark(bookmarkId);
    }
}


