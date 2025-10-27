<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset='UTF-8'>
    <meta name='viewport' content='width=device-width, initial-scale=1.0'>
    <title>Add Movie</title>
    <link rel="stylesheet" href="css/add-movie.css">
</head>
<body>

<div class='container'>
    <h1>Movies</h1>
    <div class='subtitle'>Add Movie</div>

    <form action='<%=request.getContextPath() + "/add-movie"%>' method='post' enctype='multipart/form-data'>

        <div class='form-section'>
            <h3>Title</h3>
            <div class='form-group'>
                <input type='text' id='title' name='title' placeholder='Enter movie title' required>
            </div>
        </div>


        <div class='form-section'>
            <h3>Description</h3>
            <div class='form-group'>
                <textarea id='description' name='description' placeholder='Enter movie description'></textarea>
            </div>
        </div>


        <div class='form-section'>
            <h3>Movie Details</h3>

            <div class='form-group'>
                <label for='releaseDate'>Release Date</label>
                <input type='date' id='releaseDate' name='releaseDate' required>
            </div>

            <div class='form-group'>
                <label for='genre'>Genre</label>
                <select id='genre' name='genre' required>
                    <option value=''>Select Genre</option>
                    <option value='Science Fiction'>Science Fiction</option>
                    <option value='Action'>Action</option>
                    <option value='Drama'>Drama</option>
                    <option value='Comedy'>Comedy</option>
                    <option value='Horror'>Horror</option>
                    <option value='Romance'>Romance</option>
                    <option value='Thriller'>Thriller</option>
                    <option value='Adventure'>Adventure</option>
                    <option value='Fantasy'>Fantasy</option>
                </select>
            </div>


            <div style='display: flex; gap: 20px;'>
                <div class='form-group' style='flex: 1;'>
                    <label for='rating'>Rating (0-10)</label>
                    <input type='number' id='rating' name='rating' class='rating-input' min='0' max='10' step='0.1'
                           required>
                </div>
                <div class='form-group' style='flex: 1;'>
                    <label for='duration'>Duration (minutes)</label>
                    <input type='number' id='duration' name='duration' class='duration-input' min='1' max='500'
                           required>
                </div>
            </div>
        </div>

        <div class='form-section'>
            <h3>Poster Image</h3>
            <div class='form-group'>
                <div class='file-input-wrapper'>
                    <input type='file' id='posterImage' name='pictureUrl' accept='image/*'>
                </div>
            </div>
        </div>

        <div class='form-actions'>
            <button type='submit' class='btn btn-primary'>Add Movie</button>
            <a href='<%=request.getContextPath() + "/admin"%>' class='btn btn-secondary'>Cancel</a>
        </div>
    </form>

</body>
</html>