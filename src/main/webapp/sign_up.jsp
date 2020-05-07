<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<head>
    <meta charset="utf-8">
    <script src="https://code.jquery.com/jquery-1.12.0.min.js"></script>
    <script src="https://rawgit.com/notifyjs/notifyjs/master/dist/notify.js"></script>
</head>
<body>



<form:form  method = "POST" action = "/sign_up" modelAttribute="user" onsubmit="return validate()">
<table>
    <tbody>
        <tr>
            <td><form:label   cssClass="lbl"  path = "firstname">Name</form:label></td>
            <td><form:input  name="firstname"  id="firstname" cssClass="textbox"   path = "firstname" /></td>
            <td><form:errors path = "firstname" cssClass = "error" /></td>
        </tr>
        <tr>
            <td><form:label cssClass="lbl" path = "lastname">Surname</form:label></td>
            <td> <form:input cssClass="textbox" name="lastname" id="lastname" path = "lastname" /></td>
            <td><form:errors path = "lastname" cssClass = "error" /></td>
        </tr>
        <tr>
            <td><form:label  cssClass="lbl" path = "phone_number">Phone</form:label></td>
            <td><form:input cssClass="textbox" name="phone_number" id="phone_number"  path = "phone_number" /></td>
            <td><form:errors path = "phone_number" cssClass = "error" /></td>

        </tr>
        <tr>
            <td><form:label cssClass="lbl" path = "email">Email</form:label></td>
            <td><form:input cssClass="textbox" name="email" id="email" path = "email" /></td>
            <td><form:errors path = "email" cssClass = "error" /></td>
        </tr>
        <tr>
            <td><form:label cssClass="lbl" path = "password">Password</form:label></td>
            <td><form:password cssClass="textbox" name="password" id="password"  path = "password" /></td>
            <td><form:errors path = "password" cssClass = "error" /></td>
        </tr>
        <tr>
            <td><form:label cssClass="lbl" path = "password2">Confirm password</form:label></td>
            <td><form:password cssClass="textbox" name="password2" id="password2" path = "password2" /></td>
            <td><form:errors path = "password2" cssClass = "error" /></td>
        </tr>
        <tr>
            <td colspan = "2">
                <input id="send" name="send" type = "submit" value = "Submit"/>
            </td>
        </tr>
    </tbody>
    </table>

</form:form>

<script>

    function validate() {

        var firstname = document.getElementById("firstname");
        var lastname = document.getElementById("lastname");
        var phone_number = document.getElementById("phone_number");
        var email = document.getElementById("email");
        var password = document.getElementById("password");
        var password2 = document.getElementById("password2");

        var emailRGEX = /^[-a-z0-9!#$%&'*+/=?^_`{|}~ ]+(?:\.[-a-z0-9!#$%&'*+/=?^_`{|}~]+)*@(?:[a-z0-9]([-a-z0-9]{0,61}[a-z0-9])?\.)*(?:aero|arpa|asia|biz|cat|com|coop|edu|gov|info|int|jobs|mil|mobi|museum|name|net|org|pro|tel|travel|[a-z][a-z])$/i;
        var phoneRGEX = /^((8|\+7)[\- ]?)?(\(?\d{3}\)?[\- ]?)?[\d\- ]{7,10}$/i;
        var phoneResult = phoneRGEX.test(phone_number.value);
        var emailResult = emailRGEX.test(email.value);




       

        if (!firstname.value || firstname.value.length < 1 || firstname.value.length > 16) {
            firstname.style.border = "2px solid red";


            return false;
        } else {
            firstname.style.border = "";
        }

        if (!lastname.value || lastname.value.length < 1 || lastname.value.length > 16) {
            lastname.style.border = "2px solid red";
            return false;
        } else {
            lastname.style.border = "";
        }

        if (phoneResult === false) {
            phone_number.style.border = "2px solid red";
            return false;
        } else {
            phone_number.style.border = "";
        }

        if (emailResult === false) {
            emailResult.style.border = "2px solid red";
            return false;
        } else {
            emailResult.style.border = "";
        }

        if (!password.value || password.value.length < 1 || password.value.length > 16) {
            password.style.border = "2px solid red";
            return false;
        } else {
            password.style.border = "";
        }

        if (password2 !== password) {
            password2.style.border = "2px solid red";
            return false;
        } else {
            password2.style.border = "";
        }

        return true;

    }

</script>

</body>