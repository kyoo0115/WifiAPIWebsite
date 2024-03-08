 <%@ page import="java.util.List" %>
 <%@ page import="org.example.dto.LocationHistoryDTO" %>
 <%@ page contentType="text/html;charset=UTF-8" language="java" %>
 <html>
 <head>
     <title>위치 히스토리 목록</title>
     <style>

     </style>
 </head>
 <body>
     <h2>위치 히스토리 목록</h2>

     <table>
         <thead>
             <tr>
                 <th>ID</th>
                 <th>X 좌표</th>
                 <th>Y 좌표</th>
                 <th>조회일자</th>
                 <th>비고</th>
             </tr>
         </thead>
         <tbody>

             <%
             List<LocationHistoryDTO> historyList = (List<LocationHistoryDTO>) request.getAttribute("historyList");
             if (historyList != null) {
             for (LocationHistoryDTO entry : historyList) {
             %>
             <tr>
                 <td><%= entry.getId() %></td>
                 <td><%= entry.getLatitude() %></td>
                 <td><%= entry.getLongitude() %></td>
                 <td><%= entry.getTimestamp() %></td>
                 <td><a href="/deleteHistory?id=<%= entry.getId() %>">삭제</a></td>
             </tr>
             <%
                }
             }
             %>
         </tbody>
     </table>
 </body>
 </html>
