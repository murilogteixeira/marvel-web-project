package com.marvelquiz.backend.service.interfaces;

import java.util.List;
import java.util.Optional;

import com.marvelquiz.bean.User;

public interface UserServiceInterface {
    List<User> findAll();
    Optional<User> findById(Long id);
    User save(User user);
    User update(User user);
    void delete(User user);
}