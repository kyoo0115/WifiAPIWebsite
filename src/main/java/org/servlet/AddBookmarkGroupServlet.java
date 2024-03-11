package org.servlet;

import org.dto.BookmarkGroupDTO;
import org.service.BookmarkGroupService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/addBookmarkGroup")
public class AddBookmarkGroupServlet extends HttpServlet {

    private final BookmarkGroupService service = new BookmarkGroupService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String name = request.getParameter("name");
        int displayOrder = Integer.parseInt(request.getParameter("displayOrder"));

        BookmarkGroupDTO group = new BookmarkGroupDTO(name, displayOrder);
        group.setName(name);
        group.setDisplayOrder(displayOrder);

        service.addBookmarkGroup(group);

        response.sendRedirect("manageBookmark?success=true");
    }
}
