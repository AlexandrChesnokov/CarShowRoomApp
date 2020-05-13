<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<head>
    <meta charset="utf-8">

    <meta name="viewport" content="width=device-width, initial-scale=1" />

    <!-- <link
     rel="stylesheet"
     href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
     integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk"
     crossorigin="anonymous"
   /> -->
    <link
            rel="stylesheet"
            href="https://cdnjs.cloudflare.com/ajax/libs/bootswatch/4.4.1/darkly/bootstrap.min.css"
    />
    <link
            rel="stylesheet"
            href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/css/all.min.css"
            integrity="sha256-h20CPZ0QyXlBuAw7A+KluUYx/3pK+c7lYEpqLTlxjYQ="
            crossorigin="anonymous"
    />
    <script
            src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
            integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
            crossorigin="anonymous"
    ></script>
    <script
            src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
            integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
            crossorigin="anonymous"
    ></script>
    <script
            src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"
            integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI"
            crossorigin="anonymous"
    ></script>

    <script
            src="https://cdnjs.cloudflare.com/ajax/libs/jquery.maskedinput/1.4.1/jquery.maskedinput.min.js"
            integrity="sha256-+4KHeBj6I8jAKAU8xXRMXXlH+sqCvVCoK5GAFkmb+2I="
            crossorigin="anonymous"
    ></script>

   <style>

       body {
           background-image: url("https://images.wallpaperscraft.ru/image/mercedes_benz_amg_gt_s_2016_vid_szadi_102848_1920x1200.jpg");
       }

       .reg-form {
           background-color: rgba(0, 0, 0, 0.6);
           width: 100%;
           max-width: 500px;
           position: absolute;
           left: 50%;
           top: 50%;
           transform: translateX(-50%) translateY(-50%);
       }
   </style>
</head>
<body>



<form:form class="reg-form p-3 rounded-lg mb-0"  method = "POST" action = "/sign_up" modelAttribute="user" onsubmit="return validate()">

    <fieldset>
        <legend>Registration</legend>
        <div class="form-group">
            <form:label   for="emailInput"  path = "email">Email</form:label>
            <form:input id="emailInput"  name="email"  path = "email" class="form-control" placeholder="Enter email" />
            <form:errors path = "email" cssClass = "error" />
            <div class="invalid-feedback">Incorrect email format</div>
            <div class="valid-feedback">Success!</div>
        </div>
        <div class="form-group">
            <form:label   for="firstnameInput"  path = "firstname">First name</form:label>
            <form:input id="firstnameInput" name="firstname"  path = "firstname" class="form-control" placeholder="Enter First name" />
            <form:errors path = "firstname" cssClass = "error" />
            <div class="invalid-feedback">Incorrect first name </div>
            <div class="valid-feedback">Success!</div>
        </div>
        <div class="form-group">
            <form:label   for="lastnameInput"  path = "lastname">Last name</form:label>
            <form:input id="lastnameInput" name="lastname"  path = "lastname" class="form-control" placeholder="Enter Last name" />
            <form:errors path = "lastname" cssClass = "error" />
            <div class="invalid-feedback">Incorrect last name</div>
            <div class="valid-feedback">Success!</div>
        </div>
        <div class="form-group">
            <form:label   for="phoneInput"  path = "phone_number">Phone number</form:label>
            <form:input id="phoneInput" name="phone_number"  path = "phone_number" class="form-control" placeholder="Phone number" />
            <form:errors path = "phone_number" cssClass = "error" />
            <div class="invalid-feedback">Incorrect phone number</div>
            <div class="valid-feedback">Success!</div>
        </div>
        <div class="form-group">
            <form:label   for="passInput"  path = "password">Password</form:label>
            <form:input id="passInput" name="password" type="password"  path = "password" class="form-control" placeholder="Enter password" />
            <form:errors path = "password" cssClass = "error" />
            <div class="invalid-feedback">Incorrect password</div>
            <div class="valid-feedback">Success!</div>
        </div>
        <div class="form-group">
            <form:label   for="passInput2"  path = "password2">Confirm password</form:label>
            <form:input id="passInput2" name="password2"  type="password" path = "password2" class="form-control" placeholder="Confirm password" />
            <form:errors path = "password2" cssClass = "error" />
            <div class="invalid-feedback">Password does not match</div>
            <div class="valid-feedback">Success!</div>
        </div>
        <input type="submit" class="btn btn-success btn-lg btn-block" value="Sign Up">



    </fieldset>



</form:form>

<script>
    function validate() {
        var firstname = document.getElementById("firstnameInput");

        var lastname = document.getElementById("lastnameInput");

        var phone_number = document.getElementById("phoneInput");

        var email = document.getElementById("emailInput");

        var password = document.getElementById("passInput");

        var password2 = document.getElementById("passInput2");

        var emailRGEX = /^[-a-z0-9!#$%&'*+/=?^_`{|}~ ]+(?:\.[-a-z0-9!#$%&'*+/=?^_`{|}~]+)*@(?:[a-z0-9]([-a-z0-9]{0,61}[a-z0-9])?\.)*(?:aero|arpa|asia|biz|cat|com|coop|edu|gov|info|int|jobs|mil|mobi|museum|name|net|org|pro|tel|travel|[a-z][a-z])$/i;
        var phoneRGEX = /^((8|\+7)[\- ]?)?(\(?\d{3}\)?[\- ]?)?[\d\- ]{7,10}$/i;
        var NAME_REGEX = /^([A-Z]{1}[a-z]{1,23})$/i;
        var phoneResult = phoneRGEX.test(phone_number.value);
        var emailResult = emailRGEX.test(email.value);
        var fnameResult = NAME_REGEX.test(firstname.value);
        var lnameResult = NAME_REGEX.test(lastname.value);


        var count = 0;

        if (emailResult === false) {
            email.classList.remove('is-valid');
            email.classList.add('is-invalid');
           count++;
        } else {
            email.classList.remove('is-invalid');
            email.classList.add('is-valid');
        }

        if (fnameResult === false) {
            firstname.classList.remove('is-valid');
            firstname.classList.add('is-invalid');
            count++;
        } else {
            firstname.classList.remove('is-invalid');
            firstname.classList.add('is-valid');
        }

        if (lnameResult === false) {
            lastname.classList.remove('is-valid');
            lastname.classList.add('is-invalid');
            count++;
        } else {
            lastname.classList.remove('is-invalid');
            lastname.classList.add('is-valid');
        }

        if (phoneResult === false) {
            phone_number.classList.remove('is-valid');
            phone_number.classList.add('is-invalid');
            count++;
        } else {
            phone_number.classList.remove('is-invalid');
            phone_number.classList.add('is-valid');
        }



        if (!password.value || password.value.length < 1 || password.value.length > 16) {
            password.classList.remove('is-valid');
            password.classList.add('is-invalid');
            count++;
        } else {
            password.classList.remove('is-invalid');
            password.classList.add('is-valid');
        }

        if (password2.value !== password.value) {
            password2.classList.remove('is-valid');
            password2.classList.add('is-invalid');
            count++;
        } else {
            password2.classList.remove('is-invalid');
            password2.classList.add('is-valid');
        }

        return count === 0;

    }

</script>

</body>