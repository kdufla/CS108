<%--
  Created by IntelliJ IDEA.
  User: kdufla
  Date: 5/27/17
  Time: 8:41 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Information Incorrect</title>
</head>
<body>
<h1>Please Try Again</h1>
<h2>Either your username or password is incorrect. Please try again.</h2>
<!-- https://howtoprogramwithjava.com/html-forms/ -->
<form action="LoginServlet" method="POST">
    Username: <input type="text" name="username" /><br/>
    Password: <input type="password" name="password" /><br/>
    <input type="submit" value="Submit"/>
</form>
<a href = "CreateAcc.jsp">Create account</a>
</body>
</html>
