package servlet;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.UserMovie;
import repository.UserMovieRepository;
import repository.impl.UserMovieRepositoryImpl;
import service.UserMovieService;
import service.impl.UserMovieServiceImpl;

import java.io.IOException;
import java.io.PrintWriter;

public class DeleteUserMovieServlet extends HttpServlet {
    private UserMovieService userMovieService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        UserMovieRepository userMovieRepository = new UserMovieRepositoryImpl();
        this.userMovieService = new UserMovieServiceImpl(userMovieRepository);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter writer = resp.getWriter();

        String userId = req.getParameter("user_id");
        String movieId = req.getParameter("movie_id");

        if (userId == null || movieId == null) {
            resp.getWriter().println("<h1 style='color: red'>userID and movieID are required!</h1>");
            return;
        }

        Long userIdConverted = Long.parseLong(userId);
        Long movieIdConverted = Long.parseLong(movieId);

        UserMovie userMovie = userMovieService.findByUserAndMovieId(userIdConverted,movieIdConverted).orElse(null);

        if (userMovie == null){
            writer.println("<h1 style='color: orange'>User Movie cannot find!</h1>");
            writer.println("<a href='index.jsp'>Go Back</a>");
            return;
        }

        userMovieService.deleteById(userMovie.getId());
        writer.println("<h1 style='color: green'> <b>Movie " + userMovie.getMovie().getTitle() + " Delete from User " + userMovie.getUser().getUsername() + " Successfully.</b></h1>");
        writer.println("<a href='index.jsp'>Go Back</a>");
    }
}
