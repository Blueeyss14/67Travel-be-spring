package com.example._travel_be.config;

import com.example._travel_be.features.auth.admin.model.Admin;
import com.example._travel_be.features.auth.admin.repository.AdminRepository;
import com.example._travel_be.features.auth.user.model.User;
import com.example._travel_be.features.auth.user.repository.UserRepository;
import org.springframework.stereotype.Component;

@Component
public class JwtUtils {

    private final UserRepository userRepository;
    private final AdminRepository adminRepository;
    private final JwtConfig jwtConfig;

    public JwtUtils(UserRepository userRepository, AdminRepository adminRepository, JwtConfig jwtConfig) {
        this.userRepository = userRepository;
        this.adminRepository = adminRepository;
        this.jwtConfig = jwtConfig;
    }

    public Long getUserIdFromToken(String token) {
        String actualToken = token.replace("Bearer ", "");
        String identifier = jwtConfig.getEmailFromToken(actualToken);

        User user = userRepository.findByEmail(identifier).orElse(null);
        if(user != null) {
            return user.getId();
        }

        Admin admin = adminRepository.findByUsername(identifier)
                .orElseThrow(() -> new RuntimeException("User/Admin tidak ditemukan"));
        return admin.getId();
    }

    public String getRoleFromToken(String token) {
        String actualToken = token.replace("Bearer ", "");
        String identifier = jwtConfig.getEmailFromToken(actualToken);

        User user = userRepository.findByEmail(identifier).orElse(null);
        if(user != null) {
            return user.getRole();
        }

        Admin admin = adminRepository.findByUsername(identifier)
                .orElseThrow(() -> new RuntimeException("User/Admin tidak ditemukan"));
        return admin.getRole();
    }
}