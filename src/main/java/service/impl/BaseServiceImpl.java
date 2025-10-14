package service.impl;

import model.BaseEntity;
import repository.BaseRepository;
import service.BaseService;

import java.util.List;
import java.util.Optional;

public class BaseServiceImpl<ID , T extends BaseEntity<ID> , R extends BaseRepository<ID, T>> implements BaseService<ID , T> {
    protected final R baseRepository;

    public BaseServiceImpl(R baseRepository) {
        this.baseRepository = baseRepository;
    }

    @Override
    public T saveOrUpdate(T type) {
        if (type != null) {
            return baseRepository.saveOrUpdate(type);
        }
        return null;
    }

    @Override
    public boolean deleteById(ID id) {
        if (id != null) {
            return baseRepository.deleteById(id);
        }
        return false;
    }

    @Override
    public Optional<T> findById(ID id) {
        if (id != null){
            return baseRepository.findById(id);
        }
        return Optional.empty();
    }

    @Override
    public List<T> findAll() {
        return baseRepository.findAll();
    }
}
