package org.example;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/details")
public class DetailsServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String mgrNumber = request.getParameter("mgrNumber");

        Wifi wifi = getWifiDetails(mgrNumber);

        request.setAttribute("wifi", wifi);
        request.getRequestDispatcher("details.jsp").forward(request, response);
    }

    private Wifi getWifiDetails(String mgrNumber) {
        // 이 메소드를 완성해야 합니다.
        // mgrNumber를 사용하여 데이터베이스에서 와이파이 세부 정보를 가져옵니다.
        return null;
    }
}
