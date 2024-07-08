package com.example.Brewplan.Service;

import java.util.List;

public interface CrudService<T, ID> {
    T save(T entity);

    List<T> findAll();

    T findById(ID id);

    void deleteById(ID id);
}

