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
        String name = request.getParameter("name");
        int displayOrder = Integer.parseInt(request.getParameter("displayOrder"));
        String remarks = request.getParameter("remarks");

        BookmarkGroupDTO group = new BookmarkGroupDTO(name, displayOrder, remarks);
        group.setName(name);
        group.setDisplayOrder(displayOrder);
        group.setRemarks(remarks);

        service.addBookmarkGroup(group);

        response.sendRedirect("bookmark-group.jsp?success=true");

    }
}
