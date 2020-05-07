<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: lohpi
  Date: 04.05.2020
  Time: 15:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

    <title>Welcome</title>
</head>
<body>
  <c:if test="${pageContext.request.userPrincipal.name != null}">
    <form id="logoutForm" method="post" action="/logout">
      <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    </form>
    <h2> Hello ${pageContext.request.userPrincipal.name} | <a onclick="document.forms['logoutForm'].submit()">
      logout
    </a> </h2>
  </c:if>

  <a href="/searchCars.jsp">quick search</a>

  <form id="showCars" action="" method="get">
<input name="cars" type="submit" formaction="/cars" value="Show all cars" formmethod="get"/>
  </form>

  <a href="/logout">Logout</a>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js">

</script>
</body>
</html>
