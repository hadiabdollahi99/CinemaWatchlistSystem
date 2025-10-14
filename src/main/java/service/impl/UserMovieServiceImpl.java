package service.impl;

import model.UserMovie;
import repository.UserMovieRepository;
import service.UserMovieService;

import java.util.Optional;

public class UserMovieServiceImpl extends BaseServiceImpl<Long, UserMovie, UserMovieRepository> implements UserMovieService {

    protected UserMovieRepository userMovieRepository;

    public UserMovieServiceImpl(UserMovieRepository baseRepository) {
        super(baseRepository);
        this.userMovieRepository = baseRepository;
    }

    @Override
    public Optional<UserMovie> findByUserAndMovieId(Long userId, Long movieId) {
        if (userId != null && movieId != null){
            return userMovieRepository.findByUserAndMovieId(userId, movieId);
        }
        return Optional.empty();
    }
}
