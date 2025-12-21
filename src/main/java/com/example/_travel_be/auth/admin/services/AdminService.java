package com.example._travel_be.auth.admin.services;

import com.example._travel_be.auth.admin.dto.AdminResponse;
import com.example._travel_be.auth.admin.model.Admin;
import com.example._travel_be.auth.admin.repository.AdminRepository;
import com.example._travel_be.jwt.JwtConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtConfig jwtConfig;

    public AdminResponse register(Admin admin) {
        if(adminRepository.findByUsername(admin.getUsername()).isPresent()){
            throw new RuntimeException("username is exist");
        }

        admin.setPassword(passwordEncoder.encode(admin.getPassword()));

        if(admin.getRole() == null || admin.getRole().isEmpty()){
            admin.setRole("ADMIN");
        }

        adminRepository.save(admin);

        String token = jwtConfig.generateToken(admin.getUsername());

        return new AdminResponse(
                token,
                admin.getUsername(),
                admin.getEmail(),
                "Admin has Successfully Registered"
        );
    }
}
