<%--
  Created by IntelliJ IDEA.
  User: lohpi
  Date: 04.05.2020
  Time: 0:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ page isELIgnored="false" %>
    <title>Collection</title>
</head>
<body>


<table border="1" cellpadding="2" width="100%">
    <tr>
        <th>Maker</th>
        <th>Model</th>
        <th>Action</th>
    </tr>

    <c:forEach var="car" items="${cars}">
        <tr>
            <td>${car.maker_name}</td>
            <td>${car.name}</td>
            <td><a href="/viewCar?id=${car.id}" target="_blank">View more</a></td>
        </tr>
    </c:forEach>
    <input type="button" onclick="history.back();" value="Back"/>
</table>
<a href="/logout"></a>
</body>
</html>
