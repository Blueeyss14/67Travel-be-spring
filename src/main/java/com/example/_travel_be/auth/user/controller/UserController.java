package com.example._travel_be.auth.user.controller;

import com.example._travel_be.auth.user.dto.AuthResponseUser;
import com.example._travel_be.auth.user.dto.RegisterRequestUser;
import com.example._travel_be.auth.user.model.User;
import com.example._travel_be.auth.user.services.UserService;
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
            AuthResponseUser response = userService.register(request);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
