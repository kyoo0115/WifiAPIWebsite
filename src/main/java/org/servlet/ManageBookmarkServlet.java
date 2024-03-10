//package org.servlet;
//
//import org.dto.BookmarkGroupDTO;
//import org.service.BookmarkGroupService;
//
//import javax.servlet.RequestDispatcher;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.util.List;
//
//@WebServlet("/manageBookmark")
//public class ManageBookmarkServlet extends HttpServlet {
//    private BookmarkGroupService bookmarkGroupService = new BookmarkGroupService();
//
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        List<BookmarkGroupDTO> groups = bookmarkGroupService.getAllGroups();
//        request.setAttribute("bookmarkGroups", groups);
//        RequestDispatcher dispatcher = request.getRequestDispatcher("/details.jsp");
//        dispatcher.forward(request, response);
//    }
//}
