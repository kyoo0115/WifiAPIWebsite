//package org.servlet;
//
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//
//@WebServlet("/viewBookmarks")
//public class ViewBookmarkServlet extends HttpServlet {
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        // Example logic to get bookmarks list
//        List<Bookmark> bookmarks = bookmarkService.getAllBookmarks(); // Assume this service is implemented somewhere
//        request.setAttribute("bookmarks", bookmarks);
//        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/viewBookmarks.jsp");
//        dispatcher.forward(request, response);
//    }
//}
