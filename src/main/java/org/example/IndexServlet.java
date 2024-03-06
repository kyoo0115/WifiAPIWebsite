package org.example;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet("/index")
public class IndexServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        double userLatitude = Double.parseDouble(request.getParameter("latitude"));
        double userLongitude = Double.parseDouble(request.getParameter("longitude"));

        List<Wifi> wifiList = fetchDataFromDatabase(userLatitude, userLongitude);

        request.setAttribute("wifiList", wifiList);
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

    private List<Wifi> fetchDataFromDatabase(double userLatitude, double userLongitude) {
        List<Wifi> wifiList;
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            wifiList = DatabaseHelper.getWifiDataWithDistance(userLatitude, userLongitude);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return wifiList;
    }
}
