package org.example.services;

import org.example.model.Student;
import org.example.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    StudentRepository repository;

    //Create

    public Student save(Student s){
        repository.save(s);
        return s;
    }

    //Read
    public List<Student> findAll(){
        return repository.findAll();
    }

    public Optional<Student> findById(String id){
        return repository.findById(id);
    }

    //Delete
    public void deleteById(String id){
        repository.deleteById(id);
    }
}
