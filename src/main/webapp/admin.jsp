<%--
  Created by IntelliJ IDEA.
  User: abdol
  Date: 10/26/2025
  Time: 7:36 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin Panel</title>
    <link rel="stylesheet" href="css/admin.css">
</head>
<body>
<section class="Introduction">
    <div class="container">
        <h1>
            <b>Welcome Admin <%= request.getAttribute("username")%>.</b>
        </h1>
    </div>
</section>

<section class="container">
    <div class="navigation">
        <h2>Quick Navigation</h2>

        <div class="links-grid">
            <div class="link-card">
                <a href='add-movie'>Add Movie</a>
            </div>
            <div class="link-card">
                <a href='movie-admin'>All Movie Admin</a>
            </div>
            <div class="link-card">
                <a href='logout'>Logout</a>
            </div>
        </div>
    </div>
</section>

</body>
</html>
