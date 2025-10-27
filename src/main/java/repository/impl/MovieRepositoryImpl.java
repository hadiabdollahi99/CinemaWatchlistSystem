package repository.impl;

import jakarta.persistence.EntityManager;
import model.Movie;
import model.User;
import repository.MovieRepository;
import util.EntityManagerProvider;

import java.util.Optional;

public class MovieRepositoryImpl extends BaseRepositoryImpl<Long, Movie> implements MovieRepository {
    @Override
    public Class<Movie> getEntityClass() {
        return Movie.class;
    }
}
