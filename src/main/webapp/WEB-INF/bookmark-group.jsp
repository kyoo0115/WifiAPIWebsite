<%@ page import="java.util.List" %>
<%@ page import="org.dto.LocationHistoryDTO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>위치 히스토리 목록</title>
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
    <body onload="showAlertOnSuccess()">

    <div class="page-header">
        <h2>북마크 구릅 관리</h2>
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
            <a href="bookmark-group-add.jsp" class="btn btn-primary">북마크 그룹 추가</a>
        </div>
    </div>

    <script>
    function showAlertOnSuccess() {
                const urlParams = new URLSearchParams(window.location.search);
                const success = urlParams.get('success');
                if(success === 'true') {
                    alert('Bookmark group added successfully!');
                }
            }
    </script>
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
    </body>
</html>
