package com.example._travel_be.auth.user.services;

import com.example._travel_be.jwt.JwtConfig;
import com.example._travel_be.auth.user.dto.AuthResponseUser;
import com.example._travel_be.auth.user.dto.RegisterRequestUser;
import com.example._travel_be.auth.user.model.User;
import com.example._travel_be.auth.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtConfig jwtConfig;

    public AuthResponseUser register(RegisterRequestUser request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email sudah digunakan");
        }
        if (userRepository.existsByNoTelpon(request.getNoTelpon())) {
            throw new RuntimeException("No telepon sudah digunakan");
        }
        if (!request.getPassword().equals(request.getConfirmPassword())) {
            throw new RuntimeException("Password dan konfirmasi password tidak sama");
        }

        User user = new User();
        user.setNama(request.getNama());
        user.setEmail(request.getEmail());
        user.setNoTelpon(request.getNoTelpon());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole("USER");

        userRepository.save(user);

        String token = jwtConfig.generateToken(user.getEmail());

        return new AuthResponseUser(
                token,
                user.getNama(),
                user.getEmail(),
                "Registrasi berhasil, Anda sudah login"
        );
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public boolean isAdmin(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User tidak ditemukan"));
        return "ADMIN".equals(user.getRole());
    }

    public String getRole(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User tidak ditemukan"));
        return user.getRole();
    }

}
