package com.example._travel_be.controller;

import com.example._travel_be.model.User;
import com.example._travel_be.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController("api/v1")
public class UserController {
    private final UserRepository repo;

    public UserController(UserRepository repo){
        this.repo = repo;
    }

    @GetMapping("/users")
    public List<User> getAllUser(){
        return repo.findAll();
    }

    @GetMapping("/user/{id}")
    public User getUser(@PathVariable UUID id){
        return repo.findById(id)
                .orElseThrow(() -> {
                   return null;
                });
    }

    @PutMapping("/user/{id}")
    public ResponseEntity<?> replaceUser(@PathVariable UUID id, @RequestBody User newData){
        return repo.findById(id)
                .map(user -> {
                    user.setId(id);
                    user.setEmail(newData.getEmail());
                    user.setNama(newData.getNama());
                    user.setUsername(newData.getUsername());
                    user.setPassword(newData.getPassword());
                    return ResponseEntity.status(HttpStatus.OK).body("User replaced");
                }).orElse(
                     ResponseEntity.notFound().build()
                );
    }

    @PostMapping("/users/create")
    public ResponseEntity<?> createUser(@RequestBody User newData){
        repo.save(newData);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/users/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable UUID id){
        repo.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }


}
