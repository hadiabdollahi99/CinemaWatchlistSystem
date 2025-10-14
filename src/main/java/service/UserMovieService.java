package service;

import model.UserMovie;

import java.util.Optional;

public interface UserMovieService extends BaseService<Long, UserMovie> {
    Optional<UserMovie> findByUserAndMovieId(Long userId, Long movieId);
}
