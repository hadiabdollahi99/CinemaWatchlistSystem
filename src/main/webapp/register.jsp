<%--
  Created by IntelliJ IDEA.
  User: abdol
  Date: 10/26/2025
  Time: 7:06 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register</title>
    <link rel="stylesheet" href="css/register.css">
</head>
<body>

<h1>Login</h1>
<form action="register" method="post">
    <h2>Form User Fields:</h2>

    <div class="register-form">
        <label>Username:</label>
        <input type="text" name="username" required>
    </div>

    <div class="register-form">
        <label>Email:</label>
        <input type="email" name="email" required>
    </div>

    <div class="register-form">
        <label>Password:</label>
        <input type="password" name="password" required>
    </div>

    <div class="register-form">
        <label>Submit Button:</label>
        <button type="submit" class="submit-btn">Register</button>
    </div>

</form>

</body>
</html>
