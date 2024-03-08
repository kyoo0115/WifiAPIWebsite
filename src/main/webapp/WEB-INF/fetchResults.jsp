<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>WIFI 정보 가져오기 결과</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f5f5f5;
            text-align: center;
        }

        .result-container {
            display: inline-block;
            margin-top: 50px;
        }

        .result-message {
            background-color: #4CAF50;
            color: white;
            padding: 10px 20px;
            border-radius: 5px;
            display: inline-block;
            font-size: 1.2em;
        }

        a {
            display: inline-block;
            background-color: #2196F3;
            color: white;
            padding: 10px 20px;
            border-radius: 5px;
            text-decoration: none;
            font-size: 1.2em;
            margin-top: 20px;
        }

        a:hover {
            background-color: #0b7dda;
        }

    </style>
</head>
<body>

<div class="result-container">
    <%
    Integer totalCount = (Integer) request.getAttribute("totalCount");
    if (totalCount != null) {
    %>
        <div class="result-message">
            <%= totalCount %>개의 WIFI 정보를 가져와서 저장했습니다.
        </div>
    <%
    } else {
    %>
        <div class="result-message">
            데이터를 가져오는 데 문제가 발생했습니다.
        </div>
    <%
    }
    %>
    <a href="/WifiAPIfinder">홈으로 가기</a>
</div>

</body>
</html>
