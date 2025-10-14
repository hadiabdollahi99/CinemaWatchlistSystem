package repository.impl;

import model.Movie;
import repository.MovieRepository;

public class MovieRepositoryImpl extends BaseRepositoryImpl<Long, Movie> implements MovieRepository {
    @Override
    public Class<Movie> getEntityClass() {
        return Movie.class;
    }
}
