<%--
  Created by IntelliJ IDEA.
  User: abdol
  Date: 10/26/2025
  Time: 7:09 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" href="css/login.css">
</head>
<body>

<h1>Login</h1>
<form action="login" method="post">
    <h2>Form User Fields:</h2>

    <div class="user-form">
        <label>Username:</label>
        <input type="text" name="username" required>
    </div>


    <div class="user-form">
        <label>Password:</label>
        <input type="password" name="password" required>
    </div>

    <div class="user-form">
        <label>Submit Button:</label>
        <button type="submit" class="submit-btn">Login</button>
    </div>

</form>

</body>
</html>
