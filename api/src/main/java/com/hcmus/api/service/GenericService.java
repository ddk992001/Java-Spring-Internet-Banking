package com.hcmus.api.service;

import com.hcmus.api.common.response.Response;
import com.hcmus.api.exception.GenericException;

import java.util.List;

public interface GenericService<T, I> {
    List<T> getAll();
    T getById(I id);
    void create(T object);
    void update(I id, T object);
    void deleteById(I id);
    void deleteAll();
}
