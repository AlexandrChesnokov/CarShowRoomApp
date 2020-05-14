<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Alexandr Chesnokov
  Date: 05.05.2020
  Time: 15:43
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>view</title>
</head>
<body>
<table border="1" cellpadding="2" width="100%">
    <tr>
        <th>Maker</th>
        <th>Model</th>
        <th>Price</th>
        <th>Color</th>
        <th>Year</th>
        <th>Hp</th>
        <th>Status</th>
    </tr>
    <tr>
            <td>${car.maker_name}</td>
            <td>${car.name}</td>
            <td>${car.price}</td>
            <td>${car.color}</td>
            <td>${car.year}</td>
            <td>${car.hp}</td>
            <td>${status}</td>
        </tr>



</table>

<form:form method="post" action="/orderCar?carId=${car.id}" modelAttribute="enh">

    <form:select path="id">
        <form:option value="4"></form:option>
        <form:option value="1">STAGE 1</form:option>
        <form:option value="2">STAGE 2</form:option>
        <form:option value="3">STAGE 3</form:option>
    </form:select>
    <input onclick="confirm('Are you sure?')" type="submit" value="To order">
</form:form>

<a href="/">Main menu</a>


<security:authorize access="hasAnyAuthority('ADMIN', 'MANAGER')">
    <a id="" href="/editCar?id=${car.id}">Edit</a>

    <a id="" onclick="confirm('Are you sure?')" href="/deleteCar?id=${car.id}">Delete</a>


</security:authorize>

<script>

</script>
</body>
</html>
