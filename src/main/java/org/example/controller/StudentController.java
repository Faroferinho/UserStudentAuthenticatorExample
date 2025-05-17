package org.example.controller;

import org.example.details.Constant;
import org.example.details.JWTTokenProvider;
import org.example.model.Student;
import org.example.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
public class StudentController {

    @Autowired
    private JWTTokenProvider jwtTokenValidator;

    @Autowired
    private StudentService service;

    //Create
    @PostMapping(Constant.STUDENT)
    public ResponseEntity<Student> createStudent(@RequestHeader(value = "Authorization") String token, @RequestBody Student s){
        Student newStudent = service.save(s);

        if(jwtTokenValidator.validateToken(token.substring(7))) {
            return ResponseEntity.status(HttpStatus.CREATED).body(newStudent);
        }else{
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Unauthenticated User");
        }
    }

    //Update

    //Read
    @GetMapping(Constant.STUDENT)
    public ResponseEntity<List<Student>> readAll(@RequestHeader(value = "Authorization") String token){
        if (jwtTokenValidator.validateToken(token.substring(7))) {
            return ResponseEntity.ok(service.findAll());
        } else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Unauthenticated User");
        }
    }

    @GetMapping(Constant.STUDENT + "/{id}")
    public ResponseEntity<Optional<Student>> readOne(@RequestHeader(value = "Authorization") String token, @RequestBody String id){
        if(jwtTokenValidator.validateToken(token.substring(7))){
            return ResponseEntity.ok(service.findById(id));
        }else{
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Unauthenticated User");
        }
    }

    //Delete
    @DeleteMapping(Constant.STUDENT + "/{id}")
    public ResponseEntity<Void> deleteById(@RequestHeader(value = "Authorization") String token, @RequestBody String id){
        if(jwtTokenValidator.validateToken(token.substring(7))){
            service.deleteById(id);
            return ResponseEntity.noContent().build();
        }else{
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Unauthenticated User");
        }
    }
}
