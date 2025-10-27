<%@ page import="model.Movie" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: abdol
  Date: 10/26/2025
  Time: 9:37 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset='UTF-8'>
    <meta name='viewport' content='width=device-width, initial-scale=1.0'>
    <title>My Watchlist</title>
    <link rel="stylesheet" href="css/watchlist.css">
</head>
<body>

<div class='header'>
    <h1 class='page-title'>💚My Watchlist</h1>
    <%
        List<Movie> watchlist = (List<Movie>) request.getAttribute("watchlist");
    %>
</div>
<%
    if (watchlist.isEmpty()) {
%>
<div class='empty-watchlist'>
    <h3>Your watchlist is empty</h3>
    <p>You haven't added any movies to your watchlist yet.</p>
    <a href='<%=request.getContextPath() + "/movie-user"%>' class='btn btn-primary'>Browse Movies</a>
</div>
<% } else {
    for (Movie movie : watchlist) {
%>

<div class='movie-card'>

    <% if (movie.getProfilePictureBase64() != null) { %>
    <img src='data:image/jpeg;base64,<%=movie.getProfilePictureBase64()%>' alt='<%=movie.getTitle()%>'
         class='movie-poster'>
    <% } else { %>
    <div class='default-poster'>
        No Poster<br>Available
    </div>
    <% } %>

    <div class='movie-content'>
        <h2 class='movie-title'><%=movie.getTitle()%>
        </h2>

        <div class='movie-meta'>
            <strong><%=movie.getGenre()%>
            </strong>
            &nbsp;&bull;&nbsp;
            <%=(movie.getReleaseDate() != null ? movie.getReleaseDate().toString() : "N/A")%>
        </div>

    </div>
</div>
<%
        }
    }
%>

<div class='nav-links'>
    <a href='<%=request.getContextPath() + "/movie-user"%>'>Back To Movies</a>
</div>

</body>
</html>
