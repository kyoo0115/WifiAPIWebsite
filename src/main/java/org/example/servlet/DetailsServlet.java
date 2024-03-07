package org.example.servlet;

import org.example.dao.WifiDAO;
import org.example.dto.WifiDTO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/details")
public class DetailsServlet extends HttpServlet {

    private WifiDAO wifiDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        this.wifiDAO = new WifiDAO();
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String mgrNumber = request.getParameter("mgrNumber");

        WifiDTO wifi = getWifiDetails(mgrNumber);

        request.setAttribute("wifi", wifi);
        request.getRequestDispatcher("details.jsp").forward(request, response);
    }

    private WifiDTO getWifiDetails(String mgrNumber) {
        return wifiDAO.getWifiDetailsByMgrNo(mgrNumber);
    }
}
