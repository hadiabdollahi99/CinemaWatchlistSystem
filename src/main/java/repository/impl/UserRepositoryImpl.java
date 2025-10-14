package repository.impl;

import model.User;
import repository.UserRepository;

public class UserRepositoryImpl extends BaseRepositoryImpl<Long, User> implements UserRepository {
    @Override
    public Class<User> getEntityClass() {
        return User.class;
    }
}
