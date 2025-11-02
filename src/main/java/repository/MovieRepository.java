package repository;

import model.Movie;

import java.util.List;

public interface MovieRepository extends BaseRepository<Long, Movie>{
    List<Movie> searchMovieByGenreOTitle (String searchQuery);
}
