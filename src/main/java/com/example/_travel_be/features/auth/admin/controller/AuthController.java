package com.example._travel_be.features.auth.admin.controller;

import com.example._travel_be.features.auth.admin.model.Admin;
import com.example._travel_be.features.auth.admin.dto.AuthResponse;
import com.example._travel_be.features.auth.admin.dto.LoginRequest;
import com.example._travel_be.features.auth.admin.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        try {
            AuthResponse response = authService.login(request);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Admin admin) {
        try {
            Admin newAdmin = authService.register(admin);
            return ResponseEntity.ok("Admin berhasil didaftarkan dengan username: " + newAdmin.getUsername());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
