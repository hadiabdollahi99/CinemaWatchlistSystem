package service.impl;

import model.Movie;
import repository.MovieRepository;
import service.MovieService;

public class MovieServiceImpl extends BaseServiceImpl<Long, Movie, MovieRepository> implements MovieService {
    public MovieServiceImpl(MovieRepository baseRepository) {
        super(baseRepository);
    }
}
