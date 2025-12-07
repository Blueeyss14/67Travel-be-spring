package com.example._travel_be.auth;

import com.example._travel_be.config.JwtConfig;
import com.example._travel_be.model.Admin;
import com.example._travel_be.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtConfig jwtConfig;

    public AuthResponse login(LoginRequest request) {
        Admin admin = adminRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("Username tidak ditemukan"));

        if (!passwordEncoder.matches(request.getPassword(), admin.getPassword())) {
            throw new RuntimeException("Password salah");
        }

        String token = jwtConfig.generateToken(admin.getUsername());

        return new AuthResponse(
                token,
                admin.getUsername(),
                admin.getEmail(),
                "Login berhasil"
        );
    }

    public Admin register(Admin admin) {
        if (adminRepository.existsByUsername(admin.getUsername())) {
            throw new RuntimeException("Username sudah ada");
        }

        admin.setPassword(passwordEncoder.encode(admin.getPassword()));

        if (admin.getRole() == null || admin.getRole().isEmpty()) {
            admin.setRole("ADMIN");
        }

        return adminRepository.save(admin);
    }

}
