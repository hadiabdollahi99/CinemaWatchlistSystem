package repository.impl;

import model.Movie;
import repository.MovieRepository;
import util.EntityManagerProvider;

import java.util.List;


public class MovieRepositoryImpl extends BaseRepositoryImpl<Long, Movie> implements MovieRepository {
    @Override
    public Class<Movie> getEntityClass() {
        return Movie.class;
    }

    @Override
    public List<Movie> searchMovieByGenreOTitle(String searchQuery) {
        return EntityManagerProvider
                .getEntityManager()
                .createQuery("from Movie m where lower(m.title) like lower(:query) or lower(m.Genre) like lower(:query)", Movie.class)
                .setParameter("query", "%" + searchQuery + "%")
                .getResultList();
    }
}
