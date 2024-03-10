package org.servlet;

import org.dao.WifiDAO;
import org.dto.BookmarkGroupDTO;
import org.dto.WifiDTO;
import org.service.BookmarkGroupService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/details")
public class DetailsServlet extends HttpServlet {

    private WifiDAO wifiDAO;
    private final BookmarkGroupService bookmarkGroupService = new BookmarkGroupService();

    @Override
    public void init() throws ServletException {
        super.init();
        this.wifiDAO = new WifiDAO();
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String mgrNumber = request.getParameter("mgrNumber");

        WifiDTO wifi = getWifiDetails(mgrNumber);

        List<BookmarkGroupDTO> groups = bookmarkGroupService.getAllGroups();
        request.setAttribute("groups", groups);
        request.setAttribute("wifi", wifi);
        request.getRequestDispatcher("details.jsp").forward(request, response);
    }

    private WifiDTO getWifiDetails(String mgrNumber) {
        return wifiDAO.getWifiDetailsByMgrNo(mgrNumber);
    }
}
