<%@ page import="org.example.dto.WifiDTO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Wifi Details</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f5f5f5;
            margin: 0;
            padding: 0;
        }
        table {
            width: 80%;
            margin: 20px;
            background-color: #fff;
            box-shadow: 0 0 20px rgba(0, 0, 0, 0.1);
            border-collapse: collapse;
        }

        th, td {
            text-align: center;
            padding: 10px;
            border-bottom: 1px solid #ddd;
        }

        th {
            background-color: #4CAF50;
            color: white;
            font-weight: bold;
        }

        tr:nth-child(even) {
            background-color: #f2f2f2;
        }

        tr:hover {
            background-color: #e6e6e6;
        }

        h2 {
            color: #333;
            text-align: left;
            margin-bottom: 30px;
        }

        .btn-header {
            text-align: left;
            margin-bottom: 20px;
        }

        .btn-header a {
            display: inline-block;
            margin: 0 10px;
            text-decoration: none;
            color: #fff;
            background-color: #4CAF50;
            padding: 10px 10px;
            border-radius: 5px;
            transition: background-color 0.3s;
        }
        .detail-col {
            background-color: #4CAF50;
            color: white;
            font-weight: bold;
        }

        .btn-header a:hover {
            background-color: #367c39;
        }

        .btn-header a:last-child {
            margin-right: 0;
        }
    </style>
</head>
<body>
    <h2>와이파이 정보 구하기</h2>

    <div class="btn-header">
        <a href="/WifiAPIfinder">홈</a>
        <a href="">위치 히스토리 목록</a>
        <a href="">Open API 와이파이 정보 가져오기</a>
        <a href="">북마크 보기</a>
        <a href="">북마크 그룹 관리</a>
    </div>

    <%
        WifiDTO wifi = (WifiDTO) request.getAttribute("wifi");
        if (wifi != null) {
    %>
    <table>
        <tr>
            <th>항목</th>
            <th>내용</th>
        </tr>
        <tr>
            <td class="detail-col">거리(km)</td>
            <td><%= wifi.getDistance() %></td>
        </tr>
        <tr>
            <td class="detail-col">관리번호</td>
            <td><%= wifi.getManagerNumber() %></td>
        </tr>
        <tr>
            <td class="detail-col">자치구</td>
            <td><%= wifi.getJurisdiction() %></td>
        </tr>
        <tr>
            <td class="detail-col">와이파이명</td>
            <td><%= wifi.getMainName() %></td>
        </tr>
        <tr>
            <td class="detail-col">도로명주소</td>
            <td><%= wifi.getAddress1() %></td>
        </tr>
        <tr>
            <td class="detail-col">상세주소</td>
            <td><%= wifi.getAddress2() %></td>
        </tr>
        <tr>
            <td class="detail-col">설치위치(층)</td>
            <td><%= wifi.getInstallationFloor() %></td>
        </tr>
        <tr>
            <td class="detail-col">설치유형</td>
            <td><%= wifi.getInstallationType() %></td>
        </tr>
        <tr>
            <td class="detail-col">설치기관</td>
            <td><%= wifi.getInstallationBy() %></td>
        </tr>
        <tr>
            <td class="detail-col">서비스구분</td>
            <td><%= wifi.getServiceType() %></td>
        </tr>
        <tr>
            <td class="detail-col">망종류</td>
            <td><%= wifi.getCommunicationCarrier() %></td>
        </tr>
        <tr>
            <td class="detail-col">설치년도</td>
            <td><%= wifi.getConstructionYear() %></td>
        </tr>
        <tr>
            <td class="detail-col">실내외구분</td>
            <td><%= wifi.getInOutDoor() %></td>
        </tr>
        <tr>
            <td class="detail-col">WIFI 접속환경</td>
            <td><%= wifi.getRemarks() %></td>
        </tr>
        <tr>
            <td class="detail-col">X좌표</td>
            <td><%= wifi.getLatitude() %></td>
        </tr>
        <tr>
            <td class="detail-col">Y좌표</td>
            <td><%= wifi.getLongitude() %></td>
        </tr>
        <tr>
            <td class="detail-col">작업일자</td>
            <td><%= wifi.getTimestamp() %></td>
        </tr>
    </table>
    <%
        } else {
    %>
    <p>No wifi information available.</p>
    <%
        }
    %>
</body>
</html>
