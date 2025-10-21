package service.impl;

import model.Movie;
import model.User;
import repository.MovieRepository;
import service.MovieService;

import java.util.Optional;

public class MovieServiceImpl extends BaseServiceImpl<Long, Movie, MovieRepository> implements MovieService {
    public MovieServiceImpl(MovieRepository baseRepository) {
        super(baseRepository);
    }

    @Override
    public Movie findByTitle(String title) {
        Optional<Movie> movieOptional = baseRepository.findByTitle(title);
        if (movieOptional.isPresent())
            return movieOptional.get();
        throw new IllegalArgumentException("Movie with this title not found!");
    }
}
