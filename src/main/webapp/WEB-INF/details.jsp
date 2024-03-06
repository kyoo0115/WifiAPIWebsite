<%@ page import="org.example.Wifi" %>
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

        h2 {
            color: #333;
            text-align: left;
            margin-bottom: 30px;
        }

        .btn-header {
            margin-bottom: 20px;
        }

        .btn-header a {
            margin-right: 10px;
            text-decoration: none;
            color: #333;
            position: relative;
        }

        .btn-header a::after {
            content: "|";
            margin-left: 10px;
            color: #333;
        }

        .btn-header a:last-child::after {
            content: "";
            margin-left: 0;
        }
    </style>
</head>
<body>
    <h2>와이파이 정보 구하기</h2>

    <div class="btn-header">
        <a href="">홈 |</a>
        <a href="">위치 히스토리 목록 |</a>
        <a href="">Open API 와이파이 정보 가져오기 |</a>
        <a href="">뷱마크 보기 |</a>
        <a href="">북마크 그룹 관리</a>
    </div>

    <%
        Wifi wifi = (Wifi) request.getAttribute("wifi");
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
