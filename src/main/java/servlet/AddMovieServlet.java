package servlet;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Movie;
import model.Role;
import model.User;
import repository.MovieRepository;
import repository.impl.MovieRepositoryImpl;
import service.MovieService;
import service.impl.MovieServiceImpl;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;

public class AddMovieServlet extends HttpServlet {
    private MovieRepository movieRepository;
    private MovieService movieService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        this.movieRepository = new MovieRepositoryImpl();
        this.movieService = new MovieServiceImpl(movieRepository);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {


        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession(false);

        User user = (User) session.getAttribute("user");
        if (user == null || !user.getRole().equals(Role.ADMIN)) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        out.println("<html lang='en'>");
        out.println("<head>");
        out.println("    <meta charset='UTF-8'>");
        out.println("    <meta name='viewport' content='width=device-width, initial-scale=1.0'>");
        out.println("    <title>Add Movie - Cinema System</title>");
        out.println("    <style>");
        out.println("        body {");
        out.println("            font-family: Arial, sans-serif;");
        out.println("            margin: 0;");
        out.println("            padding: 20px;");
        out.println("            background: #f5f5f5;");
        out.println("        }");
        out.println("        .container {");
        out.println("            max-width: 800px;");
        out.println("            margin: 0 auto;");
        out.println("            background: white;");
        out.println("            padding: 30px;");
        out.println("            border-radius: 8px;");
        out.println("            box-shadow: 0 2px 10px rgba(0,0,0,0.1);");
        out.println("        }");
        out.println("        h1 {");
        out.println("            text-align: center;");
        out.println("            color: #333;");
        out.println("            margin-bottom: 10px;");
        out.println("        }");
        out.println("        .subtitle {");
        out.println("            text-align: center;");
        out.println("            color: #666;");
        out.println("            margin-bottom: 30px;");
        out.println("            font-size: 18px;");
        out.println("        }");
        out.println("        .form-group {");
        out.println("            margin-bottom: 25px;");
        out.println("        }");
        out.println("        label {");
        out.println("            display: block;");
        out.println("            margin-bottom: 8px;");
        out.println("            color: #555;");
        out.println("            font-weight: bold;");
        out.println("            font-size: 14px;");
        out.println("        }");
        out.println("        input[type='text'],");
        out.println("        input[type='number'],");
        out.println("        input[type='date'],");
        out.println("        select,");
        out.println("        textarea {");
        out.println("            width: 100%;");
        out.println("            padding: 12px;");
        out.println("            border: 1px solid #ddd;");
        out.println("            border-radius: 4px;");
        out.println("            box-sizing: border-box;");
        out.println("            font-size: 14px;");
        out.println("        }");
        out.println("        textarea {");
        out.println("            height: 120px;");
        out.println("            resize: vertical;");
        out.println("            font-family: Arial, sans-serif;");
        out.println("        }");
        out.println("        input:focus,");
        out.println("        select:focus,");
        out.println("        textarea:focus {");
        out.println("            outline: none;");
        out.println("            border-color: #007bff;");
        out.println("            box-shadow: 0 0 0 2px rgba(0,123,255,0.25);");
        out.println("        }");
        out.println("        .form-section {");
        out.println("            background: #f8f9fa;");
        out.println("            padding: 20px;");
        out.println("            border-radius: 6px;");
        out.println("            margin-bottom: 25px;");
        out.println("            border-left: 4px solid #007bff;");
        out.println("        }");
        out.println("        .form-section h3 {");
        out.println("            margin-top: 0;");
        out.println("            color: #333;");
        out.println("            border-bottom: 1px solid #dee2e6;");
        out.println("            padding-bottom: 10px;");
        out.println("        }");
        out.println("        .btn {");
        out.println("            padding: 12px 30px;");
        out.println("            border: none;");
        out.println("            border-radius: 4px;");
        out.println("            cursor: pointer;");
        out.println("            font-size: 16px;");
        out.println("            text-decoration: none;");
        out.println("            display: inline-block;");
        out.println("            text-align: center;");
        out.println("            margin-right: 15px;");
        out.println("            transition: background-color 0.3s;");
        out.println("        }");
        out.println("        .btn-primary {");
        out.println("            background: #007bff;");
        out.println("            color: white;");
        out.println("        }");
        out.println("        .btn-primary:hover {");
        out.println("            background: #0056b3;");
        out.println("        }");
        out.println("        .btn-secondary {");
        out.println("            background: #6c757d;");
        out.println("            color: white;");
        out.println("        }");
        out.println("        .btn-secondary:hover {");
        out.println("            background: #545b62;");
        out.println("        }");
        out.println("        .form-actions {");
        out.println("            text-align: center;");
        out.println("            margin-top: 40px;");
        out.println("            border-top: 1px solid #eee;");
        out.println("            padding-top: 30px;");
        out.println("        }");
        out.println("        .alert {");
        out.println("            padding: 15px;");
        out.println("            border-radius: 4px;");
        out.println("            margin-bottom: 25px;");
        out.println("            border: 1px solid transparent;");
        out.println("        }");
        out.println("        .alert-success {");
        out.println("            background: #d4edda;");
        out.println("            color: #155724;");
        out.println("            border-color: #c3e6cb;");
        out.println("        }");
        out.println("        .alert-error {");
        out.println("            background: #f8d7da;");
        out.println("            color: #721c24;");
        out.println("            border-color: #f5c6cb;");
        out.println("        }");
        out.println("        .file-input-wrapper {");
        out.println("            position: relative;");
        out.println("            overflow: hidden;");
        out.println("            display: inline-block;");
        out.println("            width: 100%;");
        out.println("        }");
        out.println("        .file-input-label {");
        out.println("            display: block;");
        out.println("            padding: 12px;");
        out.println("            background: #f8f9fa;");
        out.println("            border: 2px dashed #dee2e6;");
        out.println("            border-radius: 4px;");
        out.println("            text-align: center;");
        out.println("            cursor: pointer;");
        out.println("            color: #6c757d;");
        out.println("            transition: all 0.3s;");
        out.println("        }");
        out.println("        .file-input-label:hover {");
        out.println("            background: #e9ecef;");
        out.println("            border-color: #007bff;");
        out.println("        }");
        out.println("        .form-note {");
        out.println("            font-size: 12px;");
        out.println("            color: #6c757d;");
        out.println("            margin-top: 5px;");
        out.println("            font-style: italic;");
        out.println("        }");
        out.println("        .nav-links {");
        out.println("            text-align: center;");
        out.println("            margin-top: 20px;");
        out.println("            padding-top: 20px;");
        out.println("            border-top: 1px solid #eee;");
        out.println("        }");
        out.println("        .nav-links a {");
        out.println("            color: #007bff;");
        out.println("            text-decoration: none;");
        out.println("            margin: 0 15px;");
        out.println("        }");
        out.println("        .nav-links a:hover {");
        out.println("            text-decoration: underline;");
        out.println("        }");
        out.println("        .rating-input {");
        out.println("            width: 100px !important;");
        out.println("        }");
        out.println("        .duration-input {");
        out.println("            width: 150px !important;");
        out.println("        }");
        out.println("    </style>");
        out.println("</head>");
        out.println("<body>");
        out.println("    <div class='container'>");
        out.println("        <h1>Movies</h1>");
        out.println("        <div class='subtitle'>Add Movie</div>");

        out.println("        <form action='" + request.getContextPath() + "/add-movie' method='post'");

        out.println("            <div class='form-section'>");
        out.println("                <h3>Title</h3>");
        out.println("                <div class='form-group'>");
        out.println("                    <input type='text' id='title' name='title' " +
                "placeholder='Enter movie title' required>");
        out.println("                </div>");
        out.println("            </div>");


        out.println("            <div class='form-section'>");
        out.println("                <h3>Description</h3>");
        out.println("                <div class='form-group'>");
        out.println("                    <textarea id='description' name='description' " +
                "placeholder='Enter movie description'>" + "</textarea>");
        out.println("                </div>");
        out.println("            </div>");


        out.println("            <div class='form-section'>");
        out.println("                <h3>Movie Details</h3>");

        out.println("                <div class='form-group'>");
        out.println("                    <label for='releaseDate'>Release Date</label>");
        out.println("                    <input type='date' id='releaseDate' name='releaseDate' required>");
        out.println("                </div>");

        out.println("                <div class='form-group'>");
        out.println("                    <label for='genre'>Genre</label>");
        out.println("                    <select id='genre' name='genre' required>");
        out.println("                        <option value=''>Select Genre</option>");
        out.println("                        <option value='Science Fiction'>Science Fiction</option>");
        out.println("                        <option value='Action'>Action</option>");
        out.println("                        <option value='Drama'>Drama</option>");
        out.println("                        <option value='Comedy'>Comedy</option>");
        out.println("                        <option value='Horror'>Horror</option>");
        out.println("                        <option value='Romance'>Romance</option>");
        out.println("                        <option value='Thriller>Thriller</option>");
        out.println("                        <option value='Adventure>Adventure</option>");
        out.println("                        <option value='Fantasy'>Fantasy</option>");
        out.println("                    </select>");
        out.println("                </div>");


        out.println("                <div style='display: flex; gap: 20px;'>");
        out.println("                    <div class='form-group' style='flex: 1;'>");
        out.println("                        <label for='rating'>Rating (0-10)</label>");
        out.println("                        <input type='number' id='rating' name='rating' class='rating-input' " +
                "min='0' max='10' step='0.1' required>");
        out.println("                    </div>");
        out.println("                    <div class='form-group' style='flex: 1;'>");
        out.println("                        <label for='duration'>Duration (minutes)</label>");
        out.println("                        <input type='number' id='duration' name='duration' class='duration-input' " +
                "min='1' max='500' required>");
        out.println("                    </div>");
        out.println("                </div>");
        out.println("            </div>");

        out.println("            <div class='form-section'>");
        out.println("                <h3>Poster Image</h3>");
        out.println("                <div class='form-group'>");
        out.println("                    <div class='file-input-wrapper'>");
        out.println("                        <div class='file-input-label' id='fileLabel'>");
        out.println("                            Choose file: No file chosen");
        out.println("                        </div>");
        out.println("                        <input type='file' id='posterImage' name='pictureUrl' " +
                "accept='image/*' onchange='updateFileName(this)'>");
        out.println("                    </div>");
        out.println("                </div>");
        out.println("            </div>");

        out.println("            <div class='form-actions'>");
        out.println("                <button type='submit' class='btn btn-primary'>Add Movie</button>");
        out.println("                <a href='" + request.getContextPath() + "/admin' class='btn btn-secondary'>Cancel</a>");
        out.println("            </div>");
        out.println("        </form>");

        out.println("</body>");
        out.println("</html>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        HttpSession session = req.getSession(false);

        User user = (User) session.getAttribute("user");
        if (user == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        String title = req.getParameter("title");
        String description = req.getParameter("description");
        String releaseDate = req.getParameter("releaseDate");
        LocalDate date = LocalDate.parse(releaseDate);
        String genre = req.getParameter("genre");
        String ratingStr = req.getParameter("rating");
        Double rating = Double.parseDouble(ratingStr);
        String durationStr = req.getParameter("duration");
        Integer duration = Integer.parseInt(durationStr);
        String pictureUrl = req.getParameter("pictureUrl");
        byte[] moviePicture = pictureUrl.getBytes();


        movieService.saveOrUpdate(Movie.builder().title(title).description(description).releaseDate(date).Genre(genre).rating(rating).Duration(duration).moviePicture(moviePicture).build());
        resp.sendRedirect(req.getContextPath() + "/admin");
    }
}
