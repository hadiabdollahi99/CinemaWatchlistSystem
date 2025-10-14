package service.impl;

import model.User;
import repository.UserRepository;
import service.UserService;

public class UserServiceImpl extends BaseServiceImpl<Long, User, UserRepository> implements UserService {
    public UserServiceImpl(UserRepository baseRepository) {
        super(baseRepository);
    }
}
