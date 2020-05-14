<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Alexandr Chesnokov
  Date: 04.05.2020
  Time: 15:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

    <title>Welcome</title>

  <style>
    .btn {
      display: inline-block; /* Строчно-блочный элемент */
      background: #8C959D; /* Серый цвет фона */
      color: #fff; /* Белый цвет текста */
      padding: 1rem 1.5rem; /* Поля вокруг текста */
      text-decoration: none; /* Убираем подчёркивание */
      border-radius: 3px; /* Скругляем уголки */
    }
  </style>
</head>
<body>
<h2> Hello ${firstname}!



  <a href="searchCars" class="btn">Quick search</a>


  <a href="cars" class="btn">Show all cars</a>

  <a href="logout">Logout</a>

  <security:authorize access="hasAuthority('ADMIN')">
  <a id="" href="users">Users</a>
  </security:authorize>


  <div><h1>${result}</h1></div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js">

</script>
</body>
</html>
