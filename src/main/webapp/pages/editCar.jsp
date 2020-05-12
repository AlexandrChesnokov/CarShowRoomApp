<%--
  Created by IntelliJ IDEA.
  User: Alexandr Chesnokov
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

         price:   <form:input path="price" value="${car.price}" type="number"/>
         hp:   <form:input path="hp" value="${car.hp}" type="number"/>
         color:   <form:input path="color" value="${car.color}" type="text"/>

            <input onclick="confirm('Are you sure?')" type="submit" value="Apply changes">

        </form:form>
        <a href="/">Main menu</a>
</body>
</html>
