package repository;

import model.Movie;
import model.UserMovie;

import java.util.List;
import java.util.Optional;

public interface UserMovieRepository extends BaseRepository<Long, UserMovie> {
    Optional<UserMovie> findByUserAndMovieId(Long userId, Long movieId);
    List<UserMovie> findByUsernameId (Long userId);
}
