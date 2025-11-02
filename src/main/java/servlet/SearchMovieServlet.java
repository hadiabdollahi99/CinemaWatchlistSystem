package servlet;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Movie;
import model.User;
import repository.MovieRepository;
import repository.impl.MovieRepositoryImpl;
import service.MovieService;
import service.impl.MovieServiceImpl;

import java.io.IOException;
import java.util.List;

@WebServlet("/search-movies")
public class SearchMovieServlet extends HttpServlet {
    private MovieService movieService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        MovieRepository movieRepository = new MovieRepositoryImpl();
        this.movieService = new MovieServiceImpl(movieRepository);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession(false);

        String searchQuery = req.getParameter("search");
        User user = (User) session.getAttribute("user");

        List<Movie> movies;
        if (searchQuery != null && !searchQuery.trim().isEmpty()){
            movies = movieService.searchMovieByGenreOTitle(searchQuery);
        }
        else {
            movies = movieService.findAll();
        }

        req.setAttribute("user",user);
        req.setAttribute("movies", movies);
        req.setAttribute("searchQuery", searchQuery);
        req.getRequestDispatcher("search-movie.jsp").forward(req,resp);
    }
}