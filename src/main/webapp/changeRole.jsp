<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Alxandr Chesnokov
  Date: 08.05.2020
  Time: 16:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Change role</title>
</head>
<body>
     <form:form method="post" action="/changeRole?id=${user.id}" modelAttribute="user">

         <h2>User: ${user.firstname} ${user.lastname}</h2>
         <h2>Current role: ${user.role.name}</h2>

         Enter new role: <form:input path="role.name" type="text"/>
         <input onclick="confirm('Are you sure?')" type="submit">

     </form:form>

     <a href="/">Main menu</a>

</body>
</html>
