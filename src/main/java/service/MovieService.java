package service;

import model.Movie;
import model.User;

public interface MovieService extends BaseService<Long, Movie>{
    Movie findByTitle(String title);
}
