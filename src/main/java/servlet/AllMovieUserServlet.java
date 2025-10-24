package servlet;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Movie;
import model.User;
import model.UserMovie;
import org.hibernate.query.Query;
import repository.MovieRepository;
import repository.UserMovieRepository;
import repository.UserRepository;
import repository.impl.MovieRepositoryImpl;
import repository.impl.UserMovieRepositoryImpl;
import repository.impl.UserRepositoryImpl;
import service.MovieService;
import service.UserMovieService;
import service.UserService;
import service.impl.MovieServiceImpl;
import service.impl.UserMovieServiceImpl;
import service.impl.UserServiceImpl;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;


public class AllMovieUserServlet extends HttpServlet {
    private MovieRepository movieRepository;
    private UserMovieRepository userMovieRepository;
    private MovieService movieService;
    private UserMovieService userMovieService;


    @Override
    public void init(ServletConfig config) throws ServletException {
        this.movieRepository = new MovieRepositoryImpl();
        this.userMovieRepository = new UserMovieRepositoryImpl();
        this.movieService = new MovieServiceImpl(movieRepository);
        this.userMovieService = new UserMovieServiceImpl(userMovieRepository);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession(false);

        User user = (User) session.getAttribute("user");
        if (user == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }


        List<Movie> movies = movieService.findAll();
        List<UserMovie> userMovies = userMovieService.findByUsernameId(user.getId());
        List<Movie> watchlistMovie = new ArrayList<>();
        for (UserMovie userMovie : userMovies) {
            watchlistMovie.add(userMovie.getMovie());
        }


        out.println("<html lang='en'>");
        out.println("<head>");
        out.println("    <meta charset='UTF-8'>");
        out.println("    <meta name='viewport' content='width=device-width, initial-scale=1.0'>");
        out.println("    <title>Movie Library - Cinema System</title>");
        out.println("    <style>");
        out.println("        body {");
        out.println("            font-family: Arial, sans-serif;");
        out.println("            margin: 0;");
        out.println("            padding: 20px;");
        out.println("            background: #f5f5f5;");
        out.println("            line-height: 1.6;");
        out.println("        }");
        out.println("        .header {");
        out.println("            background: white;");
        out.println("            padding: 20px;");
        out.println("            border-radius: 8px;");
        out.println("            margin-bottom: 20px;");
        out.println("            box-shadow: 0 2px 4px rgba(0,0,0,0.1);");
        out.println("        }");
        out.println("        .movie-card {");
        out.println("            background: white;");
        out.println("            padding: 25px;");
        out.println("            margin-bottom: 20px;");
        out.println("            border-radius: 8px;");
        out.println("            box-shadow: 0 2px 4px rgba(0,0,0,0.1);");
        out.println("            border-left: 4px solid #007bff;");
        out.println("        }");
        out.println("        .movie-title {");
        out.println("            font-size: 24px;");
        out.println("            margin: 0 0 15px 0;");
        out.println("            color: #333;");
        out.println("            border-bottom: 2px solid #f0f0f0;");
        out.println("            padding-bottom: 10px;");
        out.println("        }");
        out.println("        .movie-poster {");
        out.println("            width: 150px;");
        out.println("            height: 220px;");
        out.println("            object-fit: cover;");
        out.println("            border-radius: 6px;");
        out.println("            flex-shrink: 0;");
        out.println("        }");
        out.println("        .default-poster {");
        out.println("            width: 150px;");
        out.println("            height: 220px;");
        out.println("            background: #e9ecef;");
        out.println("            border-radius: 6px;");
        out.println("            display: flex;");
        out.println("            align-items: center;");
        out.println("            justify-content: center;");
        out.println("            color: #6c757d;");
        out.println("            font-size: 14px;");
        out.println("            text-align: center;");
        out.println("            flex-shrink: 0;");
        out.println("        }");
        out.println("        .movie-meta {");
        out.println("            color: #666;");
        out.println("            margin-bottom: 15px;");
        out.println("            font-size: 14px;");
        out.println("        }");
        out.println("        .movie-meta strong {");
        out.println("            color: #333;");
        out.println("        }");
        out.println("        .movie-description {");
        out.println("            line-height: 1.8;");
        out.println("            color: #444;");
        out.println("            margin-bottom: 15px;");
        out.println("        }");
        out.println("        .btn {");
        out.println("            padding: 10px 20px;");
        out.println("            border: none;");
        out.println("            border-radius: 4px;");
        out.println("            cursor: pointer;");
        out.println("            text-decoration: none;");
        out.println("            display: inline-block;");
        out.println("            font-size: 14px;");
        out.println("            transition: background-color 0.3s;");
        out.println("        }");
        out.println("        .btn-primary {");
        out.println("            background: #007bff;");
        out.println("            color: white;");
        out.println("        }");
        out.println("        .btn-primary:hover {");
        out.println("            background: #0056b3;");
        out.println("        }");
        out.println("        .btn-success {");
        out.println("            background: #28a745;");
        out.println("            color: white;");
        out.println("        }");
        out.println("        .btn-success:hover {");
        out.println("            background: #1e7e34;");
        out.println("        }");
        out.println("        .btn-secondary {");
        out.println("            background: #6c757d;");
        out.println("            color: white;");
        out.println("        }");
        out.println("        .btn-secondary:hover {");
        out.println("            background: #545b62;");
        out.println("        }");
        out.println("        .nav-links {");
        out.println("            float: right;");
        out.println("        }");
        out.println("        .user-info {");
        out.println("            background: #e9ecef;");
        out.println("            padding: 10px 15px;");
        out.println("            border-radius: 4px;");
        out.println("            margin-bottom: 15px;");
        out.println("            font-size: 14px;");
        out.println("        }");
        out.println("        .added-to-watchlist {");
        out.println("            color: #28a745;");
        out.println("            font-weight: bold;");
        out.println("            padding: 8px 15px;");
        out.println("            background: #d4edda;");
        out.println("            border-radius: 4px;");
        out.println("            display: inline-block;");
        out.println("        }");
        out.println("        .hr-divider {");
        out.println("            border: 0;");
        out.println("            height: 1px;");
        out.println("            background: #ddd;");
        out.println("            margin: 30px 0;");
        out.println("        }");
        out.println("        .rating {");
        out.println("            color: #ffc107;");
        out.println("            font-weight: bold;");
        out.println("        }");
        out.println("    </style>");
        out.println("</head>");
        out.println("<body>");

        out.println("    <div class='header'>");
        out.println("        <h1>Movie Library</h1>");
        out.println("        <div class='user-info'>");
        out.println("            Logged in as <strong>" + user.getUsername() + "</strong> (Role: " + user.getRole() + ")");
        out.println("    </div>");

        if (movies.isEmpty()) {
            out.println("    <div class='movie-card'>");
            out.println("        <h3>No movies available</h3>");
            out.println("        <p>There are no movies in the library yet.</p>");
            out.println("    </div>");
        } else {
            for (Movie movie : movies) {
                out.println("    <div class='movie-card'>");
                if (movie.getProfilePictureBase64() != null) {
                    out.println("        <img src='data:image/jpeg;base64," + movie.getProfilePictureBase64() + "' " +
                            "alt='" + movie.getTitle() + "' class='movie-poster'>");
                } else {
                    out.println("        <div class='default-poster'>");
                    out.println("            No Poster<br>Available");
                    out.println("        </div>");
                }
                out.println("        <h2 class='movie-title'>" + movie.getTitle() + "</h2>");

                out.println("        <div class='movie-meta'>");
                out.println("            <strong>" + movie.getGenre() + "</strong>");
                out.println("            &nbsp;&bull;&nbsp;");
                out.println("            " + (movie.getReleaseDate() != null ? movie.getReleaseDate().toString() : "N/A"));
                out.println("        </div>");

                out.println("        <div class='movie-meta'>");
                out.println("            <span class='rating'>Rating: " + movie.getRating() + "/10</span>");
                if (movie.getDuration() != null) {
                    out.println("            &nbsp;&bull;&nbsp;");
                    out.println("            Duration: " + movie.getDuration() + " minutes");
                }
                out.println("        </div>");

                if (movie.getDescription() != null && !movie.getDescription().isEmpty()) {
                    out.println("        <p class='movie-description'>" + movie.getDescription() + "</p>");
                }


                if (watchlistMovie.contains(movie)) {
                    out.println("        <span class='added-to-watchlist'>✓ Added to Watchlist</span>");
                } else {
                    out.println("        <form action='" + request.getContextPath() + "/movie-user' method='post' style='display: inline;'>");
                    out.println("            <input type='hidden' name='movieId' value='" + movie.getId() + "'>");
                    out.println("            <button type='submit' class='btn btn-success'>Add to Watchlist</button>");
                    out.println("        </form>");
                }

                out.println("    </div>");
                out.println("    <hr class='hr-divider'>");
            }
        }

        out.println("</body>");
        out.println("</html>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession(false);

        User user = (User) session.getAttribute("user");
        if (user == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        String movieIdStr = request.getParameter("movieId");

        if (movieIdStr == null || movieIdStr.isEmpty()) {
            response.sendRedirect(request.getContextPath() + "/movie-user");
            return;
        }

        Long movieId = Long.parseLong(movieIdStr);
        Long userId = user.getId();


        Movie movie = movieService.findById(movieId).orElse(null);
        if (movie == null) {
            response.sendRedirect(request.getContextPath() + "/movie-user");
            return;
        }

        UserMovie userMovie1 = userMovieService.findByUserAndMovieId(userId, movieId).orElse(null);

        if (userMovie1 != null) {
            response.sendRedirect(request.getContextPath() + "/movie-user");
            return;
        }

        userMovieService.saveOrUpdate(UserMovie.builder().user(user).movie(movie).build());
        session.setAttribute("user", user);


        response.sendRedirect(request.getContextPath() + "/movie-user");

    }

}
