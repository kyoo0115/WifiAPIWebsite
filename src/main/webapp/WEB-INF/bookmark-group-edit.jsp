<%@ page import="java.util.List" %>
<%@ page import="org.dto.BookmarkGroupDTO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit Bookmark Group</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f5f5f5;
        }

    </style>
</head>
<body>
    <div class="container">
        <h2>Edit Bookmark Group</h2>
        <%

        BookmarkGroupDTO group = (BookmarkGroupDTO) request.getAttribute("group");
        if (group != null) {
        %>
            <form action="/WifiAPIfinder/editBookmarkGroup" method="post">
                <input type="hidden" name="groupId" value="<%= group.getId() %>" />

                <div class="form-group">
                    <label for="name">Name:</label>
                    <input type="text" class="form-control" name="name" id="name" value="<%= group.getName() %>" required />
                </div>

                <div class="form-group">
                    <label for="displayOrder">Display Order:</label>
                    <input type="number" class="form-control" name="displayOrder" id="displayOrder" value="<%= group.getDisplayOrder() %>" required />
                </div>

                <button type="submit" class="btn btn-primary">Save Changes</button>
            </form>
        <% } else { %>
            <p>Error: No bookmark group data to display.</p>
        <% } %>
    </div>

    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</body>
</html>
