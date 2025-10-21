package repository;

import model.User;

import java.util.Optional;

public interface UserRepository extends BaseRepository<Long, User>{
    Optional<User> findByUserName(String userName);
}
