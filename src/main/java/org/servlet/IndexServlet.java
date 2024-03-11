package org.servlet;

import org.dao.WifiDAO;
import org.dto.WifiDTO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/index")
public class IndexServlet extends HttpServlet {
    private WifiDAO wifiDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        this.wifiDAO = new WifiDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        double userLatitude = Double.parseDouble(request.getParameter("latitude"));
        double userLongitude = Double.parseDouble(request.getParameter("longitude"));

        int page = 1;
        int pageSize = 20;

        try {
            if (request.getParameter("page") != null) {
                page = Integer.parseInt(request.getParameter("page"));
            }
            if (request.getParameter("pageSize") != null) {
                pageSize = Integer.parseInt(request.getParameter("pageSize"));
            }
        } catch (NumberFormatException ignored) {
        }

        List<WifiDTO> wifiList = fetchDataFromDatabase(userLatitude, userLongitude, page, pageSize);

        request.setAttribute("wifiList", wifiList);
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

    private List<WifiDTO> fetchDataFromDatabase(double userLatitude, double userLongitude, int page, int pageSize) {
        return wifiDAO.getWifiDataWithDistance(userLatitude, userLongitude, page, pageSize);
    }
}
