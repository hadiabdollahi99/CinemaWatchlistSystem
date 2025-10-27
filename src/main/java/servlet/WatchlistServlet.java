package servlet;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Movie;
import model.User;
import repository.UserMovieRepository;
import repository.impl.UserMovieRepositoryImpl;
import service.UserMovieService;
import service.impl.UserMovieServiceImpl;

import java.io.IOException;
import java.util.List;

public class WatchlistServlet extends HttpServlet {
    private UserMovieService userMovieService;


    @Override
    public void init(ServletConfig config) throws ServletException {
        UserMovieRepository userMovieRepository = new UserMovieRepositoryImpl();
        this.userMovieService = new UserMovieServiceImpl(userMovieRepository);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("user");

        List<Movie> watchlistMovie = userMovieService.findMovieByUsernameId(user.getId());

        request.setAttribute("user",user);
        request.setAttribute("watchlist",watchlistMovie);
        request.getRequestDispatcher("watchlist.jsp").forward(request,response);

    }
}