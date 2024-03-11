package org.servlet;

import org.service.BookmarkService;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/deleteBookmark")
public class DeleteBookmarkServlet extends HttpServlet {
    private BookmarkService service;

    public void init() {
        service = new BookmarkService();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int bookmarkId = Integer.parseInt(request.getParameter("bookmarkId"));
        service.deleteBookmark(bookmarkId);
        response.sendRedirect("viewBookmarks?deleteSuccess=true");
    }
}
