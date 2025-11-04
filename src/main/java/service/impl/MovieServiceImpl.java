package service.impl;

import model.Movie;
import repository.MovieRepository;
import service.MovieService;
import util.EntityManagerProvider;

import java.util.List;


public class MovieServiceImpl extends BaseServiceImpl<Long, Movie, MovieRepository> implements MovieService {
    protected MovieRepository movieRepository;

    public MovieServiceImpl(MovieRepository baseRepository) {
        super(baseRepository);
        this.movieRepository = baseRepository;
    }

    @Override
    public List<Movie> searchMovieByGenreOTitle(String searchQuery) {
        return movieRepository.searchMovieByGenreOTitle(searchQuery);
    }

    @Override
    public int getRatingCount(Long movieId) {
        Long countRating = EntityManagerProvider
                .getEntityManager()
                .createQuery("select count(c) from Comment c where c.movie.id = :movieId and c.rating is not null", Long.class)
                .setParameter("movieId",movieId)
                .getSingleResult();
        return countRating != null ? countRating.intValue() : 0;
    }

    @Override
    public double getAverageRating(Long movieId) {
        Double averageRating = EntityManagerProvider
                .getEntityManager()
                .createQuery("select avg(c.rating) from Comment c where c.movie.id = :movieId and c.rating is not null", Double.class)
                .setParameter("movieId", movieId)
                .getSingleResult();
        return averageRating != null ? Math.round(averageRating * 10.0) / 10.0 : 0.0;
    }


    @Override
    public void updateMovieRating(Movie movie) {
        double averageRating = getAverageRating(movie.getId());
        movie.setRating(averageRating * 2);
        movieRepository.saveOrUpdate(movie);
    }

    @Override
    public String getRatingStar(Long movieId) {
        StringBuilder ratingStar = new StringBuilder();
        for (int i = 1; i <= 5; i++) {
            Long count = EntityManagerProvider
                    .getEntityManager()
                    .createQuery("select count(c) from Comment c where c.movie.id = :movieId and c.rating = :rating", Long.class)
                    .setParameter("movieId", movieId)
                    .setParameter("rating", i)
                    .getSingleResult();
            if (i != 5){
                ratingStar.append(i).append("★: ").append(count != null ? count : 0).append(",  ");
                continue;
            }
            ratingStar.append(i).append("★: ").append(count != null ? count : 0).append("   ");

        }
        return ratingStar.toString().trim();
    }
}