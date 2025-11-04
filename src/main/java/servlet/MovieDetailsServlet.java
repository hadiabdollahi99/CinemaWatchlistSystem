package servlet;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Comment;
import model.Movie;
import model.User;
import repository.CommentRepository;
import repository.MovieRepository;
import repository.impl.CommentRepositoryImpl;
import repository.impl.MovieRepositoryImpl;
import service.CommentService;
import service.MovieService;
import service.impl.CommentServiceImpl;
import service.impl.MovieServiceImpl;

import java.io.IOException;
import java.util.List;

@WebServlet("/movie-details")
public class MovieDetailsServlet extends HttpServlet {
    private MovieService movieService;
    private CommentService commentService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        MovieRepository movieRepository = new MovieRepositoryImpl();
        CommentRepository commentRepository = new CommentRepositoryImpl();
        this.movieService = new MovieServiceImpl(movieRepository);
        this.commentService = new CommentServiceImpl(commentRepository);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String movieIdStr = request.getParameter("id");
        if (movieIdStr == null || movieIdStr.isEmpty()) {
            response.sendRedirect(request.getContextPath() + "/movie-user");
            return;
        }

        Long movieId = Long.parseLong(movieIdStr);
        Movie movie = movieService.findById(movieId).orElse(null);
        if (movie == null) {
            response.sendRedirect(request.getContextPath() + "/movie-user");
            return;
        }

        List<Comment> comments = commentService.findCommentByMovieId(movieId);

        int ratingCount = movieService.getRatingCount(movieId);
        double averageRating = movieService.getAverageRating(movieId);
        String ratingStar = movieService.getRatingStar(movieId);

        request.setAttribute("movie",movie);
        request.setAttribute("comments",comments);
        request.setAttribute("ratingCount",ratingCount);
        request.setAttribute("averageRating",averageRating);
        request.setAttribute("ratingStar",ratingStar);
        request.getRequestDispatcher("movie-details.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String movieIdStr = request.getParameter("movieId");
        String commentContent = request.getParameter("comment");
        String ratingStr = request.getParameter("rating");

        if (movieIdStr == null || commentContent == null || commentContent.trim().isEmpty()) {
            response.sendRedirect(request.getContextPath() + "/movie-details?id=" + movieIdStr);
            return;
        }

        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("user");

        Integer rating = (ratingStr != null && !ratingStr.isEmpty()) ? Integer.parseInt(ratingStr) : null;
        Long movieId = Long.parseLong(movieIdStr);
        Movie movie = movieService.findById(movieId).orElse( null);


        if (movie != null && user != null) {
            Comment comment = Comment.builder().content(commentContent.trim()).rating(rating).user(user).movie(movie).build();
            commentService.saveOrUpdate(comment);
            movieService.updateMovieRating(movie);
        }

        response.sendRedirect(request.getContextPath() + "/movie-details?id=" + movieId);

    }
}