package org.example.controller;

import org.example.details.Constant;
import org.example.details.JWTTokenProvider;
import org.example.model.ResponseToken;
import org.example.model.User;
import org.example.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class UserController {
    @Autowired
    private JWTTokenProvider jwtTokenValidator;

    @Autowired
    private UserService service;

    @PostMapping(Constant.USER)
    public ResponseEntity<User> createUser(@RequestBody User u){
        User newUser = service.save(u);

        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
    }

    @PostMapping(Constant.LOGIN)
    public ResponseToken login(@RequestBody User u) {
        if(service.validateLogin(u)){
            String token = jwtTokenValidator.generateToken(u.getEmail());
            return new ResponseToken("Authenticated", token);
        }
        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Incorrect User or Password");
    }

}
