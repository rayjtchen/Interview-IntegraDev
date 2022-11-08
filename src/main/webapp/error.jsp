<%--
  Created by IntelliJ IDEA.
  User: raychen
  Date: 8/11/2022
  Time: 4:18 pm
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Error Page</title>
</head>
<body>
    <h1>Error Page</h1>

    <%String errorMessage = (String) request.getAttribute("errorMessage"); %>

    <h1><%= errorMessage %></h1>
</body>
</html>
