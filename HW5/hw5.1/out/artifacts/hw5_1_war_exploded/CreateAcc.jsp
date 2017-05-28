<%--
  Created by IntelliJ IDEA.
  User: kdufla
  Date: 5/27/17
  Time: 8:35 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create Account</title>
</head>
<body>
<h1>Create Account</h1>
<!-- https://howtoprogramwithjava.com/html-forms/ -->
<form action="CreateAccServlet" method="POST">
    Username: <input type="text" name="username" /><br/>
    Password: <input type="password" name="password" /><br/>
    <input type="submit" value="Submit"/>
</form>
</body>
</html>
