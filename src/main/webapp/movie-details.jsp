<%@ page import="model.Movie" %>
<%@ page import="model.Comment" %>
<%@ page import="java.util.List" %>
<%@ page import="java.time.temporal.ChronoUnit" %><%--
  Created by IntelliJ IDEA.
  User: abdol
  Date: 10/28/2025
  Time: 3:36 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%
        Movie movie = (Movie) request.getAttribute("movie");
        List<Comment> comments = (List<Comment>) request.getAttribute("comments");
    %>
    <meta charset='UTF-8'>
    <meta name='viewport' content='width=device-width, initial-scale=1.0'>
    <title><%=movie.getTitle()%> - Movie Details</title>
    <link rel="stylesheet" href="css/movie-details.css">
</head>
<body>
<div class='container'>

    <div class='nav-links'>
        <a href='<%=request.getContextPath() + "/movie-user"%>' class='btn btn-secondary'>← Back to Movies</a>
    </div>

    <div class='movie-header'>
        <div class='movie-content'>
            <%
                if (movie.getProfilePictureBase64() != null) {
            %>

            <img src='data:image/jpeg;base64,<%=movie.getProfilePictureBase64()%>' alt='<%=movie.getTitle()%>'
                 class='movie-poster'>
            <%
            } else {
            %>
            <div class='default-poster'>No Poster Available</div>
            <%
                }
            %>
            <div class='movie-info'>
                <h1 class='movie-title'><%=movie.getTitle()%>
                </h1>
                <div class='movie-meta'>
                    <strong><%=movie.getGenre()%>
                    </strong>
                    <%=movie.getReleaseDate() != null ? movie.getReleaseDate().toString() : "N/A"%>;
                    <span class='rating'><%=movie.getRating() %>/10</span>
                </div>
                <div class='movie-meta'>
                    Duration: <%=movie.getDuration() != null ? movie.getDuration() + " minutes" : "N/A"%>
                </div>
                <p class='movie-description'><%=movie.getDescription()%>"</p>
            </div>
        </div>
    </div>


    <div class='comments-section'>
        <h2>Comments</h2>
        <div class='comment-form'>
            <form action='<%=request.getContextPath() + "/movie-details"%>' method='post'>
                <input type='hidden' name='movieId' value='<%=movie.getId()%>'>
                <textarea name='comment' placeholder='Share your thoughts about this movie...' required></textarea>
                <button type='submit' class='btn btn-success' style='margin-top: 10px;'>Post Comment</button>
            </form>
        </div>

        <%
            if (comments.isEmpty()) {
        %>

        <div class='no-comments'>
            <p>No comments yet. Be the first to share your thoughts!</p>
        </div>
        <%
        } else {
        %>
        <div class='comments-list'>
            <%
                for (Comment comment : comments) {
            %>
            <div class='comment'>
                <div class='comment-header'>
                    <div class='user-avatar'>
                        <%
                            if (comment.getUser().getProfilePictureBase64() != null) {
                        %>
                        <img src='data:image/jpeg;base64,<%=comment.getUser().getProfilePictureBase64()%>' alt='<%=comment.getUser().getUsername()%>' class='avatar-img'>
                        <%
                        } else {
                        %>
                        <div class='default-avatar'><%=comment.getUser().getUsername()%></div>
                        <%
                            }
                        %>
                    </div>
                    <div class='user-info'>
                    <span class='comment-author'><%=comment.getUser().getUsername()%> </span>
                    <span class='comment-date'> <%=comment.getCreatedAt().truncatedTo(ChronoUnit.SECONDS).toString().replace('Z', ' ').replace('T', ' ')%></span>
                </div>
                </div>
                <div class='comment-content'><%=comment.getContent()%>
                </div>
            </div>
            <%
                }
            %>
        </div>
        <%
            }
        %>
    </div>
</div>

</body>
</html>
