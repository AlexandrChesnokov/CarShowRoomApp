<%--
  Created by IntelliJ IDEA.
  User: Alexandr Chesnokov
  Date: 04.05.2020
  Time: 18:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Sign In</title>
</head>
<body>
<form action="/login/process" method="post">

 Email: <input name="email" type="email" autocomplete="off">
 Password: <input name="password" type="password" autocomplete="off">
   <input type="submit" value="sign in">
</form>

 <c:if test="${error}">
     <p>Incorrect email or password</p>
 </c:if>

<form id="registr" action="/sign_up" method="get">
    <input name="register" type="submit" value="Sign Up">
</form>
</body>
</html>
