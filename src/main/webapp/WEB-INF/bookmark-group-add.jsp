<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>북마크 그룹 추가</title>
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
<body>

<div class="container">
    <h2>북마크 그룹 추가</h2>
    <form action="addBookmarkGroup" method="post">
        <div class="form-group">
            <label for="name">그룹 이름:</label>
            <input type="text" class="form-control" id="name" name="name" required>
        </div>
        <div class="form-group">
            <label for="displayOrder">표시 순서:</label>
            <input type="number" class="form-control" id="displayOrder" name="displayOrder" required>
        </div>

        <button type="submit" class="btn btn-success">추가</button>
    </form>
</div>

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</body>
</html>
