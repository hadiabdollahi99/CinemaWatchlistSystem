package service.impl;

import jakarta.persistence.NoResultException;
import model.User;
import repository.UserRepository;
import service.UserService;

import java.util.Optional;

public class UserServiceImpl extends BaseServiceImpl<Long, User, UserRepository> implements UserService {
    public UserServiceImpl(UserRepository baseRepository) {
        super(baseRepository);
    }


    @Override
    public User findByUserName(String userName){
        Optional<User> userOptional = baseRepository.findByUserName(userName);
        if (userOptional.isPresent())
            return userOptional.get();
        throw new NoResultException("User with this username not Found!");
    }
}
