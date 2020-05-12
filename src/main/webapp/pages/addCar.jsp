<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Alexandr Chesnokov
  Date: 08.05.2020
  Time: 2:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add car</title>
</head>
<body>
<form:form  action="/addCar" method="post" modelAttribute="car">

    Maker:  <form:input path="maker_name" value="" type="text"/>
    Model:  <form:input path="name" value="" type="text"/>
    Price:   <form:input path="price" value="" type="number"/>
    Hp:       <form:input path="hp" value="" type="number"/>
    Color:   <form:input path="color" value="" type="text"/>
    Year:    <form:input path="year" value="" type="text"/>

    <input onclick="confirm('Are you sure?')" type="submit" value="Add car">

</form:form>
<a href="/">Main menu</a>
</body>
</html>
