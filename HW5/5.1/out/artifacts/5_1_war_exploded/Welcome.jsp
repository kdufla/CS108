<%--
  Created by IntelliJ IDEA.
  User: kdufla
  Date: 5/27/17
  Time: 8:32 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Welcome <%= request.getParameter("username")%></title>
</head>
<body>
    <h1>Welcome <%= request.getParameter("username")%></h1>
</body>
</html>
