package org.servlet;

import org.dao.LocationHistoryDAO;
import org.dto.LocationHistoryDTO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@WebServlet("/location-history")
public class LocationHistoryServlet extends HttpServlet {

    private LocationHistoryDAO locationHistoryDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        this.locationHistoryDAO = new LocationHistoryDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        try {
            List<LocationHistoryDTO> historyList = locationHistoryDAO.fetchLocationHistory();
            request.setAttribute("historyList", historyList);
            request.getRequestDispatcher("/history.jsp").forward(request, response);

        } catch (Exception e) {
            throw new ServletException("Failed to retrieve location history", e);
        }
    }
}
