package org.servlet;

import org.dto.BookmarkGroupDTO;
import org.service.BookmarkGroupService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/editBookmarkGroup")
public class EditBookmarkGroupServlet extends HttpServlet {
    private BookmarkGroupService service;

    @Override
    public void init() {
        service = new BookmarkGroupService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        int groupId = Integer.parseInt(request.getParameter("groupId"));
        BookmarkGroupDTO group = service.getBookmarkGroupById(groupId);
        if (group != null) {
            request.setAttribute("group", group);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/bookmark-group-edit.jsp");
            dispatcher.forward(request, response);
        } else {
            response.sendRedirect("manageBookmark?error=GroupNotFound");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String name = request.getParameter("name");
        int id = Integer.parseInt(request.getParameter("groupId"));
        int displayOrder = Integer.parseInt(request.getParameter("displayOrder"));

        BookmarkGroupDTO group = new BookmarkGroupDTO(name, displayOrder);
        group.setId(id);
        service.updateBookmarkGroup(group);

        response.sendRedirect("manageBookmark?editSuccess=true");
    }
}
