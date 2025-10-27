package servlet;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.*;
import model.Movie;
import repository.MovieRepository;
import repository.impl.MovieRepositoryImpl;
import service.MovieService;
import service.impl.MovieServiceImpl;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;


@MultipartConfig(
        maxFileSize = 1024 * 1024 * 5,
        maxRequestSize = 1024 * 1024 * 10,
        fileSizeThreshold = 1024 * 1024
)
public class EditMovieServlet extends HttpServlet {
    private MovieService movieService;


    @Override
    public void init(ServletConfig config) throws ServletException {
        MovieRepository movieRepository = new MovieRepositoryImpl();
        this.movieService = new MovieServiceImpl(movieRepository);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String movieIdStr = request.getParameter("id");
        if (movieIdStr == null || movieIdStr.isEmpty()) {
            response.sendRedirect(request.getContextPath() + "/movie-admin");
            return;
        }

        Long movieId = Long.parseLong(movieIdStr);
        Movie movie = movieService.findById(movieId).orElse(null);
        if (movie == null) {
            response.sendRedirect(request.getContextPath() + "/movie-admin");
            return;
        }

        request.setAttribute("movie", movie);
        request.getRequestDispatcher("edit-movie.jsp").forward(request, response);

    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        Part movieIdPart = req.getPart("movieId");
        String movieId = new String(movieIdPart.getInputStream().readAllBytes(), "UTF-8");
        Movie movie = movieService.findById(Long.parseLong(movieId)).orElse(null);

        Part titlePart = req.getPart("title");
        Part descriptionPart = req.getPart("description");
        Part releaseDatePart = req.getPart("releaseDate");
        Part genrePart = req.getPart("genre");
        Part ratingPart = req.getPart("rating");
        Part durationPart = req.getPart("duration");
        Part picturePart = req.getPart("pictureUrl");

        String title = new String(titlePart.getInputStream().readAllBytes(), "UTF-8");
        String description = new String(descriptionPart.getInputStream().readAllBytes(), "UTF-8");
        String releaseDate = new String(releaseDatePart.getInputStream().readAllBytes(), "UTF-8");
        String genre = new String(genrePart.getInputStream().readAllBytes(), "UTF-8");
        String rating = new String(ratingPart.getInputStream().readAllBytes(), "UTF-8");
        String duration = new String(durationPart.getInputStream().readAllBytes(), "UTF-8");


        byte[] moviePicture = null;
        if (picturePart != null && picturePart.getSize() > 0) {
            moviePicture = picturePart.getInputStream().readAllBytes();
        }


        if (movie != null) {
            if (!title.isEmpty()) {
                movie.setTitle(title);
            }
            if (!description.isEmpty()) {
                movie.setDescription(description);
            }
            if (!genre.isEmpty()) {
                movie.setGenre(genre);
            }
            if (!duration.isEmpty()) {
                movie.setDuration(Integer.parseInt(duration));
            }
            if (!releaseDate.isEmpty()) {
                movie.setReleaseDate(LocalDate.parse(releaseDate));
            }
            if (!rating.isEmpty()) {
                movie.setRating(Double.parseDouble(rating));
            }

            if (!Arrays.equals(moviePicture, movie.getMoviePicture()) && moviePicture != null) {
                movie.setMoviePicture(moviePicture);
            }
        }

        movieService.saveOrUpdate(movie);
        resp.sendRedirect(req.getContextPath() + "/admin");

    }
}



