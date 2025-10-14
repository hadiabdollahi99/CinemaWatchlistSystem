package servlet;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Movie;
import repository.MovieRepository;
import repository.impl.MovieRepositoryImpl;
import service.MovieService;
import service.impl.MovieServiceImpl;

import java.io.IOException;
import java.io.PrintWriter;

public class MovieServlet extends HttpServlet {
    private MovieRepository movieRepository;
    private MovieService movieService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        this.movieRepository = new MovieRepositoryImpl();
        this.movieService = new MovieServiceImpl(movieRepository);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter writer = resp.getWriter();


        String title = req.getParameter("title");
        String genre = req.getParameter("genre");
        String duration = req.getParameter("duration");

        if (title == null || genre == null || duration == null){
            writer.println("<h1 style='color: red'> title and genre and duration are required!</h1>");
            writer.println("<a href='index.jsp'>Go Back</a>");
            return;
        }

        Integer durationConverted = Integer.parseInt(duration);
        movieService.saveOrUpdate(Movie.builder().title(title).Genre(genre).Duration(durationConverted).build());


        writer.println("<h1 style='color: green'> <b>Movie added successfully.</b> </h1>");
        writer.println("<a href='index.jsp'>Go Back</a>");
    }
}
