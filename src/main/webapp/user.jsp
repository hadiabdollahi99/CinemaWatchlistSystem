<%--
  Created by IntelliJ IDEA.
  User: abdol
  Date: 10/26/2025
  Time: 7:27 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User Panel</title>
    <link rel="stylesheet" href="css/user.css">
</head>
<body>
<section class="Introduction">
    <div class="container">
        <h1>
            <b>Welcome User <%= request.getAttribute("username")%>.</b>
        </h1>
    </div>
</section>

<section class="container">
    <div class="navigation">
        <h2>Quick Navigation</h2>
        <div class="links-grid">
            <div class="link-card">
                <a href='edit-profile'>Edit Profile</a>
            </div>
            <div class="link-card">
                <a href='movie-user'>All Movie User</a>
            </div>
            <div class="link-card">
                <a href='watchlist'>Watchlist</a>
            </div>
            <div class="link-card">
                <a href='logout'>Logout</a>
            </div>
        </div>
    </div>
</section>

</body>
</html>
