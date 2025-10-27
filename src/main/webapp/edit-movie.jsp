<%@ page import="model.Movie" %><%--
  Created by IntelliJ IDEA.
  User: abdol
  Date: 10/26/2025
  Time: 11:55 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset='UTF-8'>
    <meta name='viewport' content='width=device-width, initial-scale=1.0'>
    <title>Edit Movie</title>
    <link rel="stylesheet" href="css/edit-movie.css">
</head>
<body>
<div class='container'>
    <h1>Edit Movie</h1>
    <%
        Movie movie = (Movie) request.getAttribute("movie");
    %>
    <div class='subtitle'>Update Movie Information</div>

    <form action='<%=request.getContextPath() + "/edit-movie"%>' method='post' enctype='multipart/form-data'>
        <input type='hidden' name='movieId' value='<%=movie.getId()%>'>
        <div class='form-section'>
            <h3>Title</h3>
            <div class='form-group'>
                <input type='text' id='title' name='title' value='<%=movie.getTitle()%>'
                       placeholder='Enter movie title' required>
            </div>
        </div>

        <div class='form-section'>
            <h3>Description</h3>
            <div class='form-group'>
                                <textarea id='description' name='description' placeholder='Enter movie description'><%=movie.getDescription() != null ? movie.getDescription() : ""%></textarea>
            </div>
        </div>

        <div class='form-section'>
            <h3>Movie Details</h3>
            <div class='form-group'>
                <label for='releaseDate'>Release Date</label>
                <input type='date' id='releaseDate' name='releaseDate'
                       value='<%=(movie.getReleaseDate() != null ? movie.getReleaseDate().toString() : "")%>' required>
            </div>

            <div class='form-group'>
                <label for='genre'>Genre</label>
                <select id='genre' name='genre' required>
                    <option value=''>Select Genre</option>
                    <option value='Science Fiction' <%=
                    (movie.getGenre() != null && movie.getGenre().equals("Science Fiction") ? " selected" : "")%>>
                        Science Fiction
                    </option>
                    <option value='Action' <%=
                    (movie.getGenre() != null && movie.getGenre().equals("Action") ? " selected" : "")%>>Action
                    </option>
                    <option value='Drama' <%=
                    (movie.getGenre() != null && movie.getGenre().equals("Drama") ? " selected" : "")%>>Drama
                    </option>
                    <option value='Comedy' <%=
                    (movie.getGenre() != null && movie.getGenre().equals("Comedy") ? " selected" : "")%>>Comedy
                    </option>
                    <option value='Horror' <%=
                    (movie.getGenre() != null && movie.getGenre().equals("Horror") ? " selected" : "")%>>Horror
                    </option>
                    <option value='Romance' <%=
                    (movie.getGenre() != null && movie.getGenre().equals("Romance") ? " selected" : "")%>>Romance
                    </option>
                    <option value='Thriller' <%=
                    (movie.getGenre() != null && movie.getGenre().equals("Thriller") ? " selected" : "")%>>Thriller
                    </option>
                    <option value='Adventure' <%=
                    (movie.getGenre() != null && movie.getGenre().equals("Adventure") ? " selected" : "")%>>Adventure
                    </option>
                    <option value='Fantasy' <%=
                    (movie.getGenre() != null && movie.getGenre().equals("Fantasy") ? " selected" : "")%>>Fantasy
                    </option>
                </select>
            </div>

            <div style='display: flex; gap: 20px;'>
                <div class='form-group' style='flex: 1;'>
                    <label for='rating'>Rating (0-10)</label>
                    <input type='number' id='rating' name='rating' class='rating-input'
                           min='0' max='10' step='0.1'
                           value='<%=(movie.getRating() != null ? movie.getRating() : "0")%>' required>
                </div>
                <div class='form-group' style='flex: 1;'>
                    <label for='duration'>Duration (minutes)</label>
                    <input type='number' id='duration' name='duration' class='duration-input'
                           min='1' max='500'
                           value='<%=(movie.getDuration() != null ? movie.getDuration() : "")%>' required>
                </div>
            </div>
        </div>


        <div class='form-section'>
            <h3>Poster Image</h3>
            <div class='form-group'>

                <%
                    if (movie.getProfilePictureBase64() != null) {
                %>
                <div style='margin-bottom: 15px;'>
                    <strong>Current Poster:</strong>
                    <div>
                        <img src='data:image/jpeg;base64,<%=movie.getProfilePictureBase64()%>' alt='Current Poster'
                             class='current-poster'>
                    </div>
                </div>
                <%
                } else {
                %>

                <div style='margin-bottom: 15px;'>
                    <strong>Current Poster:</strong>
                    <div class='no-poster'>No Poster Available</div>
                </div>
                <%
                    }
                %>

                <div class='file-input-wrapper'>
                    <input type='file' id='posterImage' name='pictureUrl'
                           accept='image/*'>
                </div>
            </div>
        </div>


        <div class='form-actions'>
            <button type='submit' class='btn btn-success'>Update Movie</button>
            <a href='<%=request.getContextPath() + "/movie-admin"%>' class='btn btn-secondary'>Cancel</a>
        </div>
    </form>
</div>

</body>
</html>