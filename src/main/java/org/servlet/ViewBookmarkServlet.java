package org.servlet;

import org.dto.WifiBookmarkDTO;
import org.service.BookmarkService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/viewBookmarks")
public class ViewBookmarkServlet extends HttpServlet {

    private static final BookmarkService bookmarkService = new BookmarkService();
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<WifiBookmarkDTO> bookmarks = bookmarkService.getAllBookmarks();
        request.setAttribute("bookmarks", bookmarks);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/bookmark-view.jsp");
        dispatcher.forward(request, response);
    }
}
