package com.example._travel_be.auth.admin.controller;

import com.example._travel_be.auth.admin.dto.AdminResponse;
import com.example._travel_be.auth.admin.model.Admin;
import com.example._travel_be.auth.admin.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins = "*")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Admin admin) {
        try{
            AdminResponse newAdmin = adminService.register(admin);
            return ResponseEntity.ok(newAdmin);
        }catch(Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
