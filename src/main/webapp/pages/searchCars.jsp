<%--
  Created by IntelliJ IDEA.
  User: Alexandr Chesnokov
  Date: 04.05.2020
  Time: 15:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <script>

        var request = new XMLHttpRequest();
        function searchInfo() {
            var name = document.carForm.carName.value;
            var url = "searchCarsByMaker?maker="+name;
            try {
                request.onreadystatechange = function () {
                    if (request.readyState == 4) {
                        var val = request.responseText;
                        document.getElementById("myLocation").innerHTML = val;
                    }
                }
                request.open("GET", url);
                request.send();
            } catch (e) {
                alert("Unable connect to server");
            }
        }

        function searchByParams() {
            var fromYear = document.paramsForm.fromYear.value;
            var toYear = document.paramsForm.toYear.value;
            var fromPrice = document.paramsForm.fromPrice.value;
            var toPrice = document.paramsForm.toPrice.value;
            var colorName = document.paramsForm.colorName.value;
            var fromHp = document.paramsForm.fromHp.value;
            var toHp = document.paramsForm.toHp.value;
            var url = "searchCarsByParams?fromYear="+fromYear+"&toYear="+toYear
            +"&fromPrice="+fromPrice+"&toPrice="+toPrice+"&colorName="+colorName
            +"&fromHp="+fromHp+"&toHp="+toHp;
            try {
                request.onreadystatechange = function () {
                    if (request.readyState == 4) {
                        var val = request.responseText;
                        document.getElementById("myLocation").innerHTML = val;
                    }
                }
                request.open("GET", url);
                request.send();
            } catch (e) {
                alert("Unable connect to server");
            }
        }
    </script>
    <title>searchCars</title>
</head>
<body>
<h1>Quick search</h1>
<form name="carForm">
    <input type="text" name="carName" >
    <input type="button" name="button" onclick="searchInfo()" value="search">
</form>

<a id="" href="addCar">Add car</a>

<form name="paramsForm">
    <h2>Year</h2>
  from:  <select name="fromYear">
    <option value=""></option>
    <option value="2007">2007</option>
    <option value="2008">2008</option>
    <option value="2009">2009</option>
    <option value="2010">2010</option>
    <option value="2011">2011</option>
    <option value="2012">2012</option>
    <option value="2013">2013</option>
    <option value="2014">2014</option>
    <option value="2015">2015</option>
    <option value="2016">2016</option>
    <option value="2017">2017</option>
    <option value="2018">2018</option>
    <option value="2019">2019</option>
    <option value="2020">2020</option>
    </select>

    to:  <select name="toYear">
    <option value=""></option>
    <option value="2007">2007</option>
    <option value="2008">2008</option>
    <option value="2009">2009</option>
    <option value="2010">2010</option>
    <option value="2011">2011</option>
    <option value="2012">2012</option>
    <option value="2013">2013</option>
    <option value="2014">2014</option>
    <option value="2015">2015</option>
    <option value="2016">2016</option>
    <option value="2017">2017</option>
    <option value="2018">2018</option>
    <option value="2019">2019</option>
    <option value="2020">2020</option>
</select>
     <h2>Price</h2>
     from:
     <input type="number" name="fromPrice" value="0"/>

    to:
    <input type="number" name="toPrice" value="1000000"/>

    <h2>Color</h2>
      <select name="colorName">
    <option value=""></option>
    <option value="yellow">yellow</option>
    <option value="black">black</option>
    <option value="red">red</option>
    <option value="gray">gray</option>
    <option value="blue">blue</option>
    <option value="white">white</option>
</select>

    <h2>Hp</h2>
    from:
    <input type="number" name="fromHp" value="0"/>

    to:
    <input type="number" name="toHp" value="5000"/>

   <p> <input type="button" name="button" onclick="searchByParams()" value="search"></p>
</form>

<a href="./">Main menu</a>

<span id="myLocation"></span>
</body>
</html>
