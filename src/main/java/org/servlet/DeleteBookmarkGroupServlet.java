package org.servlet;

import org.service.BookmarkGroupService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/deleteBookmarkGroup")
public class DeleteBookmarkGroupServlet extends HttpServlet {
    private BookmarkGroupService service;

    public void init() {
        service = new BookmarkGroupService();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));

        service.deleteBookmarkGroup(id);

        response.sendRedirect("manageBookmark?deleteSuccess=true");
    }
}