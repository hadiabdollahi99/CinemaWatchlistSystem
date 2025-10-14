package repository;

import model.BaseEntity;

import java.util.List;
import java.util.Optional;

public interface BaseRepository<ID, T extends BaseEntity<ID>>{
    T saveOrUpdate (T type);

    boolean deleteById (ID id);

    Optional<T> findById (ID id);

    List<T> findAll();

    Class<T> getEntityClass();
}
