package com.hcmus.api.controller;

import org.springframework.http.ResponseEntity;

import java.util.List;

public interface GenericController<T, I> {
    ResponseEntity<List<T>> getAll();
    ResponseEntity<T> getById(I id);
    ResponseEntity<Void> add(T object);
    ResponseEntity<Void> update(I id, T object);
    ResponseEntity<Void> deleteById(I id);
    ResponseEntity<Void> deleteAll();
}
