package org.servlet;

import org.dao.OpenAPIDAO;
import org.dao.WifiDAO;
import org.dto.WifiDTO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/FetchDataServlet")
public class FetchDataServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        OpenAPIDAO openAPIDAO = new OpenAPIDAO();
        WifiDAO wifiDAO = new WifiDAO();

        try {
            List<WifiDTO> wifiData = openAPIDAO.fetchDataFromOpenAPI();

            wifiDAO.insertWifiServiceData(wifiData);

            int totalCount = wifiData.size();
            request.setAttribute("totalCount", totalCount);

        } catch (Exception e) {
            request.setAttribute("error", "An error occurred while fetching data: " + e.getMessage());
        }

        request.getRequestDispatcher("/fetchResults.jsp").forward(request, response);
    }
}
