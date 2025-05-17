package org.example.services;

import org.example.model.User;
import org.example.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository repository;

    public User save(User u){
        repository.save(u);
        return u;
    }

    public List<User> listAll(){
        return repository.findAll();
    }

    public Optional<User> findById(String id){
        return repository.findById(id);
    }

    public void deleteById(String id){
        repository.deleteById(id);
    }

    public boolean validateLogin(User u){
        if(repository.findById(u.getEmail()).isPresent()){
            User UserInDB = repository.findById(u.getEmail()).get();
            return Objects.equals(UserInDB.getPassword(), u.getPassword());
        }
        return false;
    }

}
