package repository.impl;

import model.UserMovie;
import repository.UserMovieRepository;
import util.EntityManagerProvider;

import java.util.Optional;

public class UserMovieRepositoryImpl extends BaseRepositoryImpl<Long, UserMovie> implements UserMovieRepository {
    @Override
    public Class<UserMovie> getEntityClass() {
        return UserMovie.class;
    }


    @Override
    public Optional<UserMovie> findByUserAndMovieId(Long userId, Long movieId) {
        return EntityManagerProvider
                        .getEntityManager()
                        .createQuery("select u from UserMovie u where u.user.id = :userId and u.movie.id = :movieId", UserMovie.class)
                        .setParameter("userId", userId)
                        .setParameter("movieId", movieId)
                        .getResultStream()
                        .findFirst();
    }
}
