<%@ page import="java.util.List" %>
<%@ page import="org.dto.WifiDTO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Wifi Details</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f5f5f5;
        }

        .page-header {
            background-color: black;
            color: white;
            padding: 10px 0;
            margin-bottom: 20px;
        }

        .page-header h2 {
            margin-left: 10px;
        }

        .btn-header .btn {
            margin-right: 10px;
        }

        .btn-header .btn:last-child {
            margin-right: 0;
        }

        table {
            box-shadow: 0 0 20px rgba(0, 0, 0, 0.1);
            width: 100%;
            margin-bottom: 20px;
            background-color: #fff;
            border-collapse: collapse;
        }

        th, td {
            word-break: keep-all;
            text-align: center;
            vertical-align: middle;
            padding: 8px;
            border: 1px solid #ddd;
        }

        th {
            background-color: #343a40;
            color: white;
        }

        td.detail-col, th.detail-col {
            background-color: black;
            color: white;
        }

        .btn-danger {
            color: #fff;
            background-color: #dc3545;
            border-color: #dc3545;
        }

        .btn-danger:hover {
            color: #fff;
            background-color: #c82333;
            border-color: #bd2130;
        }

        .btn-view-favorites {
            background-color: #17a2b8;
            color: white;
        }

        .btn-view-favorites:hover {
            background-color: #138496;
        }

        .btn-manage-groups {
            background-color: #fd7e14;
            color: white;
        }

        .btn-manage-groups:hover {
            background-color: #e36c09;
        }

        @media screen and (max-width: 768px) {
            .btn-header {
                flex-direction: column;
                align-items: flex-start;
            }
        }
    </style>

</head>
<body>
    <div class="page-header">
        <h2>와이파이 정보 구하기</h2>
    </div>

    <div class="container">
        <div class="btn-header mb-3">
            <a href="/WifiAPIfinder" class="btn btn-success">홈</a>
            <a href="#" class="btn btn-view-favorites">즐겨 찾기 보기</a>
            <a href="#" class="btn btn-manage-groups">즐겨 찾기 그룹 관리</a>
            <form action="FetchDataServlet" method="post" class="d-inline">
                <input type="submit" value="OpenAPI 정보 가져오기" class="btn btn-primary" />
            </form>
            <form action="location-history" method="get" class="d-inline">
                <input type="submit" value="위치 히스토리 목록" class="btn btn-info" />
            </form>
        </div>
        <%
            WifiDTO wifi = (WifiDTO) request.getAttribute("wifi");
            if (wifi != null) {
        %>
        <table>
            <thead class="thead-dark">
            <tr>
                <th class="detail-col">항목</th>
                <th>내용</th>
            </tr>
            </thead>
            <tbody>
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
            </tbody>
        </table>
        <%
            } else {
        %>
        <p>No wifi information available.</p>
        <%
            }
        %>
    </div>

    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</body>
</html>
