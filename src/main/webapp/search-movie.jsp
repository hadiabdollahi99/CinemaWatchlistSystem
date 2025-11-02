<%@ page import="model.User" %>
<%@ page import="model.Movie" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: abdol
  Date: 11/2/2025
  Time: 8:09 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset='UTF-8'>
    <meta name='viewport' content='width=device-width, initial-scale=1.0'>
    <title>Search Movies</title>
    <link rel="stylesheet" href="css/search-movie.css">
</head>
<body>
<%
    User user = (User) request.getAttribute("user");
    List<Movie> movies = (List<Movie>) request.getAttribute("movies");
    String searchQuery = (String) request.getAttribute("searchQuery");
%>

<div class='header'>
    <h1>Search Movies</h1>
    <p>Logged in as <strong><%=user.getUsername() %>
    </strong></p>

    <form action='search-movies' method='get' class='search-box'>
        <input type='text' name='search' class='search-input' placeholder='Search by title or genre...'
               value='<%=searchQuery != null ? searchQuery : ""%>'>
        <button type='submit' class='search-btn'>Search</button>
    </form>
</div>

<%
    if (searchQuery != null && !searchQuery.trim().isEmpty()) {
%>
<div class='search-results-info'>
    Found <%=movies.size()%> results for = "<%=searchQuery%>"
</div>
<%
    }
    if (movies.isEmpty()) {
%>
<div class='no-results'>
    <h3>No movies found</h3>
    <p>Try different search terms or browse all movies.</p>
</div>
<%
} else {
    for (Movie movie : movies) {
%>
<div class='movie-card'
     onclick='window.location="<%=request.getContextPath() + "/movie-details?id=" + movie.getId()%>"'>
    <%
        if (movie.getProfilePictureBase64() != null) {
    %>
    <img src='data:image/jpeg;base64,<%=movie.getProfilePictureBase64()%>' alt='<%=movie.getTitle()%>'
         class='movie-poster'>
    <% } else {
    %>
    <div class='default-poster'>
        No Poster<br>Available
    </div>
    <%
        }
    %>
    <h2 class='movie-title'><%=movie.getTitle()%>
    </h2>
    <div class='movie-meta'>
        <span class='movie-genre'><%=movie.getGenre()%></span>
        • <%=(movie.getReleaseDate() != null ? movie.getReleaseDate().toString() : "N/A")%>
        • Rating: <%=movie.getRating()%>/10
    </div>
    <%
        if (movie.getDescription() != null && !movie.getDescription().isEmpty()) {
    %>
    <p><%=movie.getDescription()%>
    </p>
    <%
        }
    %>
</div>
<%
        }
    }
%>

</body>
</html>