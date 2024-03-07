<%@ page import="java.util.List" %>
<%@ page import="org.example.dto.WifiDTO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>와이파이 정보 구하기</title>
      <style>
          body {
              font-family: Arial, sans-serif;
              background-color: #f5f5f5;
              margin: 0;
              padding: 0;
          }

          h2 {
              color: #333;
              text-align: left;
              margin-bottom: 20px;
          }

          table {
              width: 100%;
              margin-bottom: 20px;
              background-color: #fff;
              box-shadow: 0 0 20px rgba(0, 0, 0, 0.1);
          }

          th, td {
              text-align: left;
              padding: 5px;
              border-bottom: 1px solid #ddd;
              word-break: keep-all;
          }

          th {
              background-color: #4CAF50;
              color: white;
              font-weight: bold;
              padding: 5px;
          }

          tr:nth-child(even) {
              background-color: #f2f2f2;
          }

          tr:hover {
              background-color: #e6e6e6;
          }
          .pagination-controls {
                  text-align: center;
                  margin-top: 20px;
              }

          .pagination-controls a, .pagination-controls span {
              display: inline-block;
              padding: 8px 16px;
              margin: 0 4px;
              border: 1px solid #ddd;
              background-color: #4CAF50;
              color: #fff;
              text-decoration: none;
              transition: background-color 0.3s, color 0.3s;
          }

          .pagination-controls a:hover {
              background-color: #367c39;
              color: #fff;
          }


          @media screen and (max-width: 768px) {
              table, th, td {
                  display: block;
              }

              th {
                  position: sticky;
                  top: 0;
                  z-index: 2;
              }

          }
      </style>


</head>
<body>
    <h2>와이파이 정보 구하기</h2>

    <div class="btn-header">
        <a href="/WifiAPIfinder">홈 |</a>
        <a href="">위치 히스토리 목록 |</a>
        <a href="">Open API 와이파이 정보 가져오기 |</a>
        <a href="">즐겨 찾기 보기 |</a>
        <a href="">즐겨 찾기 그룹 관리</a>
    </div>

    <div class="input-header">
        <label>LAT:</label>
        <label>
            <input id="latitude-input" name="latitude-input" type="number" placeholder="X좌표"/>
        </label>

        <label>, LNT:</label>
        <label>
            <input id="longitude-input" name="longitude-input" type="number" placeholder="Y좌표"/>
        </label>

        <button id="myLocationButton" type="button" class="btn btn-danger" onclick="getLocation()">내 위치 가져오기</button>
        <button id="nearbyWifiButton" type="button" onclick="fetchNearbyWifi()">근처 WIFI 정보 보기</button>
    </div>

    <table id="wifiTable">
        <thead>
            <tr>
                <th>거리(Km)</th>
                <th>관리번호</th>
                <th>자치구</th>
                <th>와이파이명</th>
                <th>도로명주소</th>
                <th>상세주소</th>
                <th>설치위치(세부)</th>
                <th>설치유형</th>
                <th>설치기관</th>
                <th>서비스구분</th>
                <th>망비임구분</th>
                <th>설치년도</th>
                <th>실내외구분</th>
                <th>WIFI접속환경</th>
                <th>X좌표</th>
                <th>Y좌표</th>
                <th>작업일자</th>
            </tr>
        </thead>
        <tbody>
            <% List<WifiDTO> wifiList = (List<WifiDTO>) request.getAttribute("wifiList");
                if (wifiList != null) {
                    for (WifiDTO wifi : wifiList) { %>
                        <tr>
                            <td><%= wifi.getDistance() %></td>
                            <td><%= wifi.getManagerNumber() %></td>
                            <td><%= wifi.getJurisdiction() %></td>
                            <td><a href="details?mgrNumber=<%= wifi.getManagerNumber() %>"><%= wifi.getMainName() %></a></td>
                            <td><%= wifi.getAddress1() %></td>
                            <td><%= wifi.getAddress2() %></td>
                            <td><%= wifi.getInstallationFloor() %></td>
                            <td><%= wifi.getInstallationType() %></td>
                            <td><%= wifi.getInstallationBy() %></td>
                            <td><%= wifi.getServiceType() %></td>
                            <td><%= wifi.getCommunicationCarrier() %></td>
                            <td><%= wifi.getConstructionYear() %></td>
                            <td><%= wifi.getInOutDoor() %></td>
                            <td><%= wifi.getRemarks() %></td>
                            <td><%= wifi.getLatitude() %></td>
                            <td><%= wifi.getLongitude() %></td>
                            <td><%= wifi.getTimestamp() %></td>
                        </tr>
                    <% }
                } %>
        </tbody>
    </table>
     <div class="pagination-controls">
            <a href="index?latitude=<%= request.getParameter("latitude") %>&longitude=<%= request.getParameter("longitude") %>&page=1&pageSize=20">First</a>
            <% int currentPage = Integer.parseInt(request.getParameter("page") != null ? request.getParameter("page") : "1");
               int pageSize = Integer.parseInt(request.getParameter("pageSize") != null ? request.getParameter("pageSize") : "20");
               if (currentPage > 1) {
            %>
                <a href="index?latitude=<%= request.getParameter("latitude") %>&longitude=<%= request.getParameter("longitude") %>&page=<%= currentPage - 1 %>&pageSize=<%= pageSize %>">Previous</a>
            <%
               }
            %>
            <span>Page <%= currentPage %></span>
            <a href="index?latitude=<%= request.getParameter("latitude") %>&longitude=<%= request.getParameter("longitude") %>&page=<%= currentPage + 1 %>&pageSize=<%= pageSize %>">Next</a>
        </div>

    <script>
        function getLocation() {
           if (navigator.geolocation) {
             navigator.geolocation.getCurrentPosition(function (pos) {
                 var latitude = pos.coords.latitude;
                 var longitude = pos.coords.longitude;

                 document.getElementById('latitude-input').value = latitude;
                 document.getElementById('longitude-input').value = longitude;
             });
         } else {
             alert("이 브라우저에서는 좌표가 지원되지 않습니다.")
         }
     }
        function fetchNearbyWifi() {
            var latitude = document.getElementById('latitude-input').value;
            var longitude = document.getElementById('longitude-input').value;

            window.location.href = 'index?latitude=' + latitude + '&longitude=' + longitude;
        }
    </script>
</body>
</html>