package com.marvelquiz.backend.service;

import java.util.List;
import java.util.Optional;

public interface ServiceInterface<T> {
    List<T> findAll();
    Optional<T> findById(Long id);
    T save(T user);
    T update(T user);
    void delete(T user);
}