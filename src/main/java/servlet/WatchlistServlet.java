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
import repository.MovieRepository;
import repository.UserMovieRepository;
import repository.impl.MovieRepositoryImpl;
import repository.impl.UserMovieRepositoryImpl;
import service.MovieService;
import service.UserMovieService;
import service.impl.MovieServiceImpl;
import service.impl.UserMovieServiceImpl;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class WatchlistServlet extends HttpServlet {
    private UserMovieRepository userMovieRepository;
    private UserMovieService userMovieService;


    @Override
    public void init(ServletConfig config) throws ServletException {
        this.userMovieRepository = new UserMovieRepositoryImpl();
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

        List<UserMovie> userMovies = userMovieService.findByUsernameId(user.getId());
        List<Movie> watchlist = new ArrayList<>();
        for (UserMovie userMovie : userMovies) {
            watchlist.add(userMovie.getMovie());
        }

        out.println("<html lang='en'>");
        out.println("<head>");
        out.println("    <meta charset='UTF-8'>");
        out.println("    <meta name='viewport' content='width=device-width, initial-scale=1.0'>");
        out.println("    <title>My Watchlist - Cinema System</title>");
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
        out.println("            padding: 25px;");
        out.println("            border-radius: 8px;");
        out.println("            margin-bottom: 25px;");
        out.println("            box-shadow: 0 2px 4px rgba(0,0,0,0.1);");
        out.println("            text-align: center;");
        out.println("        }");
        out.println("        .page-title {");
        out.println("            font-size: 32px;");
        out.println("            margin: 0 0 15px 0;");
        out.println("            color: #333;");
        out.println("        }");
        out.println("        .subtitle {");
        out.println("            font-size: 18px;");
        out.println("            color: #666;");
        out.println("            margin-bottom: 20px;");
        out.println("        }");
        out.println("        .movie-card {");
        out.println("            background: white;");
        out.println("            padding: 25px;");
        out.println("            margin-bottom: 20px;");
        out.println("            border-radius: 8px;");
        out.println("            box-shadow: 0 2px 4px rgba(0,0,0,0.1);");
        out.println("            border-left: 4px solid #28a745;");
        out.println("            display: flex;");
        out.println("            align-items: flex-start;");
        out.println("            gap: 20px;");
        out.println("        }");
        out.println("        .movie-poster {");
        out.println("            width: 120px;");
        out.println("            height: 180px;");
        out.println("            object-fit: cover;");
        out.println("            border-radius: 6px;");
        out.println("            flex-shrink: 0;");
        out.println("        }");
        out.println("        .default-poster {");
        out.println("            width: 120px;");
        out.println("            height: 180px;");
        out.println("            background: #e9ecef;");
        out.println("            border-radius: 6px;");
        out.println("            display: flex;");
        out.println("            align-items: center;");
        out.println("            justify-content: center;");
        out.println("            color: #6c757d;");
        out.println("            font-size: 12px;");
        out.println("            text-align: center;");
        out.println("            flex-shrink: 0;");
        out.println("        }");
        out.println("        .movie-content {");
        out.println("            flex: 1;");
        out.println("        }");
        out.println("        .movie-title {");
        out.println("            font-size: 22px;");
        out.println("            margin: 0 0 12px 0;");
        out.println("            color: #333;");
        out.println("        }");
        out.println("        .movie-meta {");
        out.println("            color: #666;");
        out.println("            margin-bottom: 12px;");
        out.println("            font-size: 14px;");
        out.println("        }");
        out.println("        .movie-meta strong {");
        out.println("            color: #333;");
        out.println("        }");
        out.println("        .movie-description {");
        out.println("            line-height: 1.7;");
        out.println("            color: #444;");
        out.println("            margin-bottom: 15px;");
        out.println("        }");
        out.println("        .added-date {");
        out.println("            font-size: 12px;");
        out.println("            color: #999;");
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
        out.println("            margin-right: 10px;");
        out.println("        }");
        out.println("        .btn-danger {");
        out.println("            background: #dc3545;");
        out.println("            color: white;");
        out.println("        }");
        out.println("        .btn-danger:hover {");
        out.println("            background: #c82333;");
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
        out.println("        .empty-watchlist {");
        out.println("            background: white;");
        out.println("            padding: 50px;");
        out.println("            text-align: center;");
        out.println("            border-radius: 8px;");
        out.println("            box-shadow: 0 2px 4px rgba(0,0,0,0.1);");
        out.println("            color: #666;");
        out.println("        }");
        out.println("        .empty-watchlist h3 {");
        out.println("            margin: 0 0 15px 0;");
        out.println("            color: #333;");
        out.println("        }");
        out.println("        .nav-links {");
        out.println("            text-align: center;");
        out.println("            margin-top: 30px;");
        out.println("            padding-top: 20px;");
        out.println("            border-top: 1px solid #eee;");
        out.println("        }");
        out.println("        .nav-links a {");
        out.println("            color: #007bff;");
        out.println("            text-decoration: none;");
        out.println("            margin: 0 15px;");
        out.println("            font-size: 16px;");
        out.println("        }");
        out.println("        .nav-links a:hover {");
        out.println("            text-decoration: underline;");
        out.println("        }");
        out.println("        .rating {");
        out.println("            color: #ffc107;");
        out.println("            font-weight: bold;");
        out.println("        }");
        out.println("        .movie-actions {");
        out.println("            margin-top: 15px;");
        out.println("        }");
        out.println("    </style>");
        out.println("</head>");
        out.println("<body>");

        out.println("    <div class='header'>");
        out.println("        <h1 class='page-title'>💚My Watchlist</h1>");
        out.println("    </div>");

        if (watchlist.isEmpty()) {
            out.println("    <div class='empty-watchlist'>");
            out.println("        <h3>Your watchlist is empty</h3>");
            out.println("        <p>You haven't added any movies to your watchlist yet.</p>");
            out.println("        <a href='" + request.getContextPath() + "/movie-user' class='btn btn-primary'>Browse Movies</a>");
            out.println("    </div>");
        } else {
            for (Movie movie : watchlist) {
                out.println("    <div class='movie-card'>");

                if (movie.getProfilePictureBase64() != null) {
                    out.println("        <img src='data:image/jpeg;base64," + movie.getProfilePictureBase64() + "' " +
                            "alt='" + movie.getTitle() + "' class='movie-poster'>");
                } else {
                    out.println("        <div class='default-poster'>");
                    out.println("            No Poster<br>Available");
                    out.println("        </div>");
                }

                out.println("        <div class='movie-content'>");
                out.println("            <h2 class='movie-title'>" + movie.getTitle() + "</h2>");

                out.println("            <div class='movie-meta'>");
                out.println("                <strong>" + movie.getGenre() + "</strong>");
                out.println("                &nbsp;&bull;&nbsp;");
                out.println("                " + (movie.getReleaseDate() != null ? movie.getReleaseDate().toString() : "N/A"));
                out.println("            </div>");

                out.println("        </div>");
                out.println("    </div>");
            }
        }

        out.println("    <div class='nav-links'>");
        out.println("        <a href='" + request.getContextPath() + "/movie-user'>Back To Movies</a>");
        out.println("    </div>");

        out.println("</body>");
        out.println("</html>");
    }
}
