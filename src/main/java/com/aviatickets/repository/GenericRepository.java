package com.aviatickets.repository;

import java.util.List;

public interface GenericRepository<T, ID> {
    T save(T t);
    T getById(ID id);
    List<T> getAll();
    T update(T t);
    void deleteByObject(T t);
    void delete(ID id);
}
