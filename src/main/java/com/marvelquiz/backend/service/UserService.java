package com.marvelquiz.backend.service;

import java.util.List;
import java.util.Optional;

import com.marvelquiz.backend.repository.UserRepository;
import com.marvelquiz.bean.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements ServiceInterface<User> {
	@Autowired
	private UserRepository repository;

	@Override
	public List<User> findAll() {
		return repository.findAll();
	}

	@Override
	public Optional<User> findById(Long id) {
		return repository.findById(id);
	}

	public Optional<User> findByUsername(String username) {
		return repository.findByUsername(username);
	}

	@Override
	public User save(User user) {
		return repository.save(user);
	}

	@Override
	public User update(User user) {
		return repository.save(user);
	}

	@Override
	public void delete(User user) {
		repository.delete(user);
	}

}
