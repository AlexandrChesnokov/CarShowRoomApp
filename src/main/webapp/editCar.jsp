<%--
  Created by IntelliJ IDEA.
  User: lohpi
  Date: 07.05.2020
  Time: 18:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>editCar</title>
</head>
<body>
        <form:form name="editForm" action="/editCar?id=${car.id}" method="post" modelAttribute="car">

            <input id="editPrice" value="${car.price}" type="number">

            <input id="editColor" value="${car.color}" type="text">

            <input id="editHp" value="${car.hp}" type="number">

            <input onclick="confirm('Are you sure?')" type="submit" value="Edit">
        </form:form>


<script>


</script>
</body>
</html>
