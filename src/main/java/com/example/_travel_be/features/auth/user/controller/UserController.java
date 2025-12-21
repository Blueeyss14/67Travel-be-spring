package com.example._travel_be.features.auth.user.controller;

import com.example._travel_be.features.auth.user.dto.AuthResponseUser;
import com.example._travel_be.features.auth.user.dto.LoginRequestUser;
import com.example._travel_be.features.auth.user.dto.RegisterRequestUser;
import com.example._travel_be.features.auth.user.model.User;
import com.example._travel_be.features.auth.user.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequestUser request) {
        try {
            User user = userService.register(request);
            return ResponseEntity.ok("User berhasil didaftarkan: " + user.getNama());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestUser request) {
        try {
            AuthResponseUser response = userService.login(request);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<?> editUser(@PathVariable Long id, @RequestBody RegisterRequestUser request) {
        try {
            User updatedUser = userService.updateUser(id, request);
            return ResponseEntity.ok("User berhasil diupdate: " + updatedUser.getNama());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        try {
            userService.deleteUser(id);
            return ResponseEntity.ok("User berhasil dihapus");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
