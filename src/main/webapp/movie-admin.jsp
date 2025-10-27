<%@ page import="model.User" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Movie" %><%--
  Created by IntelliJ IDEA.
  User: abdol
  Date: 10/26/2025
  Time: 11:19 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset='UTF-8'>
    <meta name='viewport' content='width=device-width, initial-scale=1.0'>
    <title>Movie Management</title>
    <link rel="stylesheet" href="css/movie-admin.css">
</head>
<body>
<div class='header'>
    <h1 class='page-title'>Movie Management</h1>
    <%
        User user = (User) request.getAttribute("user");
        List<Movie> movies = (List<Movie>) request.getAttribute("movies");
    %>
    <div class='subtitle'>Admin Panel - All Movies</div>
    <div class='user-info'>
        Logged in as <strong><%= user.getUsername()%>
    </strong> (Role: <%= user.getRole()%>)
    </div>
    <div style='clear: both;'></div>
</div>
<%
    if (movies.isEmpty()) {
%>
<div class='empty-movies'>
    <h3>No movies available</h3>
    <p>There are no movies in the library yet. Start by adding a new movie.</p>
    <a href='<%=request.getContextPath() + "/add-movie"%>' class='btn btn-success'>Add Your First Movie</a>
</div>
<%
} else {
    for (Movie movie : movies) {
%>
<div class='movie-card'>
    <%
        if (movie.getProfilePictureBase64() != null) {
    %>
    <img src='data:image/jpeg;base64,<%=movie.getProfilePictureBase64()%>'
         alt='<%=movie.getTitle()%>' class='movie-poster'>
    <%
    } else {
    %>
    <div class='default-poster'>
        No Poster<br>Available
    </div>
    <%
        }
    %>

    <div class='movie-content'>
        <div class='movie-id'>Movie ID: <%= movie.getId()%>
        </div>
        <h2 class='movie-title'><%= movie.getTitle()%>
        </h2>

        <div class='movie-meta'>
            <strong><%= movie.getGenre()%>
            </strong>
            &nbsp;&bull;&nbsp;
            <%=(movie.getReleaseDate() != null ? movie.getReleaseDate().toString() : "N/A")%>
        </div>

        <div class='movie-meta'>
            <span class='rating'>Rating: <%=movie.getRating() %>/10</span>
            <%
                if (movie.getDuration() != null) {
            %>
            &nbsp;&bull;&nbsp;
            Duration: <%= movie.getDuration()%> minutes
            <%
                }
            %>
        </div>

        <%
            if (movie.getDescription() != null && !movie.getDescription().isEmpty()) {
        %>
        <p class='movie-description'><%= movie.getDescription()%>
        </p>
        <%
            }
        %>
        <div class='movie-actions'>
            <a href='<%=request.getContextPath() + "/edit-movie?id=" + movie.getId()%>'
               class='btn btn-warning'>Edit Movie</a>
            <form action='<%=request.getContextPath() + "/movie-admin"%>' method='post' style='display: inline;'>
                <input type='hidden' name='movieId' value='<%=movie.getId()%>'>
                <input type='hidden' name='action' value='delete'>
                <button type='submit' class='btn btn-danger'>Delete Movie</button>
            </form>
        </div>
    </div>
</div>
<%
        }
    }
%>

</body>
</html>