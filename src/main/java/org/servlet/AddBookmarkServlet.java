package org.servlet;

import org.service.BookmarkGroupService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/addWifiBookmark")
public class AddBookmarkServlet extends HttpServlet {

    private BookmarkGroupService bookmarkService;

    public void init() {
        bookmarkService = new BookmarkGroupService();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String wifiId = request.getParameter("wifiId");
        int bookmarkGroupId = Integer.parseInt(request.getParameter("bookmarkGroupId"));

        bookmarkService.addWifiToBookmarkGroup(wifiId, bookmarkGroupId);

        response.sendRedirect("details.jsp?success=true");
    }
}
