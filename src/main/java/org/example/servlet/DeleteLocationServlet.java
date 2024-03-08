package org.example.servlet;

import org.example.dao.LocationHistoryDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/DeleteLocationServlet")
public class DeleteLocationServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        LocationHistoryDAO dao = new LocationHistoryDAO();
        try {
            dao.deleteRecord(id);
            response.sendRedirect("location-history.jsp");
        } catch (SQLException e) {
            throw new ServletException("SQL Error deleting record", e);
        }
    }
}
