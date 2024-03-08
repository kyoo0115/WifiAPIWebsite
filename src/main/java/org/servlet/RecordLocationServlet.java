package org.servlet;

import com.google.gson.Gson;
import org.dao.LocationHistoryDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

@WebServlet("/record-location")
public class RecordLocationServlet extends HttpServlet {

    private LocationHistoryDAO locationHistoryDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        locationHistoryDAO = new LocationHistoryDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        StringBuilder buffer = new StringBuilder();
        BufferedReader reader = request.getReader();
        String line;
        while ((line = reader.readLine()) != null) {
            buffer.append(line);
        }
        String requestData = buffer.toString();

        Gson gson = new Gson();
        LocationRequest locationRequest = gson.fromJson(requestData, LocationRequest.class);

        try {
            double latitude = locationRequest.latitude;
            double longitude = locationRequest.longitude;
            locationHistoryDAO.recordLocation(latitude, longitude);
            response.setStatus(HttpServletResponse.SC_OK);
        } catch (Exception e) {

            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            throw new ServletException("Failed to record location", e);
        }
    }

    static class LocationRequest {
        double latitude;
        double longitude;
    }
}
