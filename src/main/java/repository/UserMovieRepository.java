package repository;

import model.UserMovie;

import java.util.Optional;

public interface UserMovieRepository extends BaseRepository<Long, UserMovie> {
    Optional<UserMovie> findByUserAndMovieId(Long userId, Long movieId);
}
