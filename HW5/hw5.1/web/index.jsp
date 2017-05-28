<%--
  Created by IntelliJ IDEA.
  User: kdufla
  Date: 5/27/17
  Time: 7:47 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <meta charset="UTF-8">
  <title>Welcome</title>
</head>
<body>
<h1>Welcome To Homework 5</h1>
<!-- https://howtoprogramwithjava.com/html-forms/ -->
<form action="LoginServlet" method="POST">
  Username: <input type="text" name="username" /><br/>
  Password: <input type="password" name="password" /><br/>
  <input type="submit" value="Submit"/>
</form>
<a href = "CreateAcc.jsp">Create account</a>
</body>
</html>
