package servlet;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Movie;
import model.User;
import model.UserMovie;
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

public class UserMovieServlet extends HttpServlet {
    private UserRepository userRepository;
    private MovieRepository movieRepository;
    private UserMovieRepository userMovieRepository;
    private UserService userService;
    private MovieService movieService;
    private UserMovieService userMovieService;


    @Override
    public void init(ServletConfig config) throws ServletException {
        this.userRepository = new UserRepositoryImpl();
        this.movieRepository = new MovieRepositoryImpl();
        this.userMovieRepository= new UserMovieRepositoryImpl();
        this.userService = new UserServiceImpl(userRepository);
        this.movieService = new MovieServiceImpl(movieRepository);
        this.userMovieService = new UserMovieServiceImpl(userMovieRepository);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter writer = resp.getWriter();

        String userId = req.getParameter("user_id");
        String movieId = req.getParameter("movie_id");

        if (userId == null || movieId == null) {
            writer.println("<h1 style='color: red'>userID and movieID are required!</h1>");
            writer.println("<a href='index.jsp'>Go Back</a>");
            return;
        }

        Long userIdConverted = Long.parseLong(userId);
        Long movieIdConverted = Long.parseLong(movieId);

        Movie movie = movieService.findById(movieIdConverted).orElse(null);
        User user = userService.findById(userIdConverted).orElse(null);

        if (movie == null || user == null){
            writer.println("<h1 style='color: orange'>movie or user cannot find!</h1>");
            writer.println("<a href='index.jsp'>Go Back</a>");
            return;
        }

        userMovieService.saveOrUpdate(UserMovie.builder().user(user).movie(movie).build());
        writer.println("<h1 style='color: green'> <b>Movie added to your watchlist.</b></h1>");
        writer.println("<a href='index.jsp'>Go Back</a>");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter writer = resp.getWriter();

        String userId = req.getParameter("user_id");

        if (userId == null) {
            writer.println("<h1 style='color: red'>userID is required!</h1>");
            writer.println("<a href='index.jsp'>Go Back</a>");
            return;
        }

        Long userIdConverted = Long.parseLong(userId);
        User user = userService.findById(userIdConverted).orElse(null);

        if (user == null){
            writer.println("<h1 style='color: orange'>user cannot find!</h1>");
            writer.println("<a href='index.jsp'>Go Back</a>");
            return;
        }

        writer.println("<html><head>");
        writer.println("<style>");
        writer.println("body {background-color: #879cbc;\n" +
                "            color: #333533;\n" +
                "            line-height: 1.6;\n" +
                "            font-style: italic; }");
        writer.println("h1 {\n" +
                "            color: #213c57;\n" +
                "            margin-bottom: 1rem;\n" +
                "            text-align: center;\n" +
                "            font-size: 2.5rem;\n" +
                "            border-bottom: 3px solid #213c57;\n" +
                "            padding-bottom: 0.5rem;\n" +
                "        }");
        writer.println(".watchlist-table {\n" +
                "            width: 50%;\n" +
                "            border-collapse: collapse;\n" +
                "            margin-top: 1.5rem;\n" +
                "            box-shadow: 0 3px 5px rgba(0, 0, 0, 0.5);\n" +
                "            border-radius: 6px;\n" +
                "        }");
        writer.println(".watchlist-table th {\n" +
                "            background-color: #2c3e50;\n" +
                "            color: white;\n" +
                "            padding: 1rem;\n" +
                "            text-align: left;\n" +
                "        }");
        writer.println(".watchlist-table td {\n" +
                "            padding: 1rem;\n" +
                "            border-bottom: 1px solid #2c3e50;\n" +
                "        }");
        writer.println(".watchlist-table tr {\n" +
                "            background-color: whitesmoke;\n" +
                "        }");
        writer.println(".watchlist-table tr:hover {\n" +
                "            background-color: #b99dc3;\n" +
                "        }");
        writer.println("</style></head><body>");
        writer.println("<h2>Watchlist for: " + user.getUsername() + "</h2>");
        writer.println("<table class=\"watchlist-table\">");
        writer.println("<tr><th>Movie Title</th><th>Genre</th><th>Duration</th></tr>");

        for (UserMovie userMovie : user.getWatchlist()){
            writer.println("<tr>");
            writer.println("<td>" + userMovie.getMovie().getTitle() + "</td>");
            writer.println("<td>" + userMovie.getMovie().getGenre() + "</td>");
            writer.println("<td>" + userMovie.getMovie().getDuration() + " min</td>");
            writer.println("</tr>");
        }
        writer.println("</table>");
        writer.println("<br><a href='index.jsp'>Go Back</a>");
        writer.println("</body></html>");
    }
}