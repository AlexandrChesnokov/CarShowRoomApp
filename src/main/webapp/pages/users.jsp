
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.alex.model.User" %><%--
  Created by IntelliJ IDEA.
  User: Alexandr Chesnokov
  Date: 13.04.2020
  Time: 0:02
  To change this template use File | Settings | File Templates.
--%>

<html>
<head>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ page isELIgnored="false" %>
    <title>ds</title>
</head>
<body>



<table border="1" cellpadding="2" width="100%">
    <tr>
        <th>Name</th>
        <th>Lastname</th>
        <th>Phone</th>
        <th>email</th>
        <th>manager</th>
        <th>password</th>
        <th>role</th>
        <th>action</th>
    </tr>

        <c:forEach var="user" items="${users}">
    <tr>
            <td>${user.firstname}</td>
            <td>${user.lastname}</td>
            <td>${user.phone_number}</td>
            <td>${user.email}</td>
            <td>${user.manager_id}</td>
            <td>${user.password}</td>
            <td>${user.role.name}</td>
            <td><a href="/changeRole?id=${user.id}">Change role</a></td>
    </tr>
        </c:forEach>

    <a href="/">Main menu</a>

</table>
<a href="/logout">Logout</a>
</body>
</html>
