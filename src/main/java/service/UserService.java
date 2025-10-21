package service;

import model.User;

import java.util.Optional;

public interface UserService extends BaseService<Long, User> {
    User findByUserName(String userName);
}
