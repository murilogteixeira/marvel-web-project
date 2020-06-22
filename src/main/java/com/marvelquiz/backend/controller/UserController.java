package com.marvelquiz.backend.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.marvelquiz.backend.service.UserService;
import com.marvelquiz.bean.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService service;

    @RequestMapping(value = "/api/user", method = RequestMethod.GET)
    public List<User> findAll() {
        return service.findAll();
    }

    @RequestMapping(value = "/api/user/{id}", method = RequestMethod.GET)
    public ResponseEntity<User> findById(@PathVariable(value = "id") long id) {
        Optional<User> user = service.findById(id);
        if (user.isPresent())
            return new ResponseEntity<User>(user.get(), HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/api/user/{username}", method = RequestMethod.GET)
    public ResponseEntity<User> findByUsername(@PathVariable(value = "username") String username) {
        Optional<User> user = service.findByUsername(username);
        if (user.isPresent())
            return new ResponseEntity<User>(user.get(), HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/api/user", method = RequestMethod.POST)
    public User save(@Valid @RequestParam(value = "username") String username, @RequestParam(value = "password") String password) {
        User user = new User();
        user.setPassword(password);
        user.setUsername(username);
        user.setScore(0);
        user.setRightAnswerPercent(0);
        user.setWrongAnswerPercent(0);
        return service.save(user);
    }

    @RequestMapping(value = "/api/user/{id}", method = RequestMethod.PUT)
    public ResponseEntity<User> update(@PathVariable(value = "id") long id, @Valid @RequestBody User newPessoa) {
        Optional<User> oldUser = service.findById(id);
        if (oldUser.isPresent()) {
            User user = oldUser.get();
            user.setUsername(newPessoa.getUsername());
            user.setPassword(newPessoa.getPassword());
            user.setScore(newPessoa.getScore());
            user.setRightAnswerPercent(newPessoa.getRightAnswerPercent());
            user.setWrongAnswerPercent(newPessoa.getWrongAnswerPercent());
            service.save(user);
            return new ResponseEntity<User>(user, HttpStatus.OK);
        } else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/api/user/{username}", method = RequestMethod.PUT)
    public ResponseEntity<User> updatePassword(@PathVariable(value = "username") String username, @Valid @RequestBody User newPessoa) {
        Optional<User> oldUser = service.findByUsername(username);
        if (oldUser.isPresent()) {
            User user = oldUser.get();
            user.setPassword(newPessoa.getPassword());
            service.save(user);
            return new ResponseEntity<User>(user, HttpStatus.OK);
        } else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/api/user/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> delete(@PathVariable(value = "id") long id) {
        Optional<User> user = service.findById(id);
        if (user.isPresent()) {
            service.delete(user.get());
            return new ResponseEntity<>(HttpStatus.OK);
        } else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
