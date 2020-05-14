<html>
<head>
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

    <style>
        body {
            background-image: url("https://images.wallpaperscraft.com/image/car_red_sports_car_142598_1920x1080.jpg");
        }

        .login-form {
            background-color: rgba(0, 0, 0, 0.3);
            width: 100%;
            max-width: 500px;
            position: absolute;
            left: 50%;
            top: 50%;
            transform: translateX(-50%) translateY(-50%);
        }

        .h100vh {
            height: 100vh;
        }
    </style>
    <title>Sign In</title>
</head>

<body>
<form
        action="login/process"
        method="post"
        class="login-form p-3 rounded-lg mb-0"
>
    <fieldset>
        <legend>Sign In</legend>
        <div class="form-group">
            <label for="exampleInputEmail1">Email address</label>
            <input
                    name="email"
                    type="email"
                    class="form-control ${error != null ? "is-invalid" : ""}"
                    id="exampleInputEmail1"
                    aria-describedby="emailHelp"
                    placeholder="Enter email"
            />
            <small id="emailHelp" class="form-text text-muted"
            >We'll never share your email with anyone else.</small
            >
            <div class="invalid-feedback">Incorrect email of password</div>
        </div>
        <div class="form-group ${error != null ? "is-invalid" : ""}">
            <label for="exampleInputPassword1">Password</label>
            <input
                    name="password"
                    type="password"
                    class="form-control"
                    id="exampleInputPassword1"
                    placeholder="Password"
            />
        </div>
        <button type="submit" class="btn btn-primary mr-2">
            <i class="fas fa-sign-in-alt"></i> Log In
        </button>
        <a href="sign_up" class="btn btn-outline-success"
        ><i class="fas fa-user-plus"></i> Sign Up</a
        >
    </fieldset>
</form>
</body>
</html>