package service;

import model.Movie;

import java.util.List;

public interface MovieService extends BaseService<Long, Movie>{
    List<Movie> searchMovieByGenreOTitle (String searchQuery);
    void updateMovieRating(Movie movie);
    double getAverageRating(Long movieId);
    int getRatingCount(Long movieId);
    String getRatingStar(Long movieId);
}