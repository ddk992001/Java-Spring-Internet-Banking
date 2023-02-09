package com.hcmus.api.controller;

import com.hcmus.api.common.response.Response;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface GenericController<T, I> {
    ResponseEntity<List<T>> getAll();
    ResponseEntity<T> getById(I id);
    ResponseEntity<Response> add(T object);
    ResponseEntity<Response> update(I id, T object);
    ResponseEntity<Response> deleteById(I id);
    ResponseEntity<Response> deleteAll();
}
