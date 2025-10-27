package servlet;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.*;
import model.Movie;
import model.User;
import repository.MovieRepository;
import repository.impl.MovieRepositoryImpl;
import service.MovieService;
import service.impl.MovieServiceImpl;

import java.io.IOException;
import java.time.LocalDate;


@MultipartConfig(
        maxFileSize = 1024 * 1024 * 5,
        maxRequestSize = 1024 * 1024 * 10,
        fileSizeThreshold = 1024 * 1024
)
public class AddMovieServlet extends HttpServlet {
    private MovieService movieService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        MovieRepository movieRepository = new MovieRepositoryImpl();
        this.movieService = new MovieServiceImpl(movieRepository);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.sendRedirect(request.getContextPath()+"/add-movie.jsp");

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

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

        movieService.saveOrUpdate(Movie.builder().title(title).description(description).releaseDate(LocalDate.parse(releaseDate)).Genre(genre).rating(Double.parseDouble(rating)).Duration(Integer.parseInt(duration)).moviePicture(moviePicture).build());
        resp.sendRedirect(req.getContextPath() + "/admin");
    }
}
