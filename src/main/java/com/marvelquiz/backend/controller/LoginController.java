package com.marvelquiz.backend.controller;

import java.sql.Timestamp;
import java.util.Optional;

import javax.validation.Valid;

import com.marvelquiz.backend.model.ApiResponse;
import com.marvelquiz.backend.service.UserService;
import com.marvelquiz.bean.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Autowired
    private UserService service;

    final String loginRequestMapping = "/api/login";

    @RequestMapping(value = loginRequestMapping, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse> login(@Valid @RequestParam(value = "username") String username, @RequestParam(value = "password") String password) {
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        System.out.println(ts + " Request : " + loginRequestMapping);

        ApiResponse apiResponse = new ApiResponse();

        Optional<User> u = service.findByUsername(username);

        System.out.println("User User: " + username + " Password User: " + password);
        System.out.println("User U: " + u.get().getUsername() + " Password U: " + u.get().getPassword());

        if (u.isPresent() && u.get().getPassword().equals(password)) {
            // apiResponse.setStatus(HttpStatus.OK);
            apiResponse.setMessage("Usuário logado.");
            return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.OK);
        } else {
            // apiResponse.setStatus(HttpStatus.UNAUTHORIZED);
            apiResponse.setMessage("Usuário ou senha inválido");
            return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.UNAUTHORIZED);
        }
    }

}