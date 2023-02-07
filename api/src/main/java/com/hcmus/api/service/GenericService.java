package com.hcmus.api.service;

import com.hcmus.api.common.response.Response;
import com.hcmus.api.exception.GenericException;

import java.util.List;

public interface GenericService<T, I> {
    List<T> getAll();
    T getById(I id) throws GenericException;
    Response create(T object);
    Response update(I id, T object) throws GenericException;
    Response deleteById(I id) throws GenericException;
    Response deleteAll();
}
