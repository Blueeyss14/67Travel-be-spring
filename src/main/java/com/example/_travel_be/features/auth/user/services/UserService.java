package com.example._travel_be.features.auth.user.services;

import com.example._travel_be.config.JwtConfig;
import com.example._travel_be.features.auth.user.dto.AuthResponseUser;
import com.example._travel_be.features.auth.user.dto.LoginRequestUser;
import com.example._travel_be.features.auth.user.dto.RegisterRequestUser;
import com.example._travel_be.features.auth.user.model.User;
import com.example._travel_be.features.auth.user.repository.UserRepository;
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

    public AuthResponseUser login(LoginRequestUser request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Email tidak ditemukan"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Password salah");
        }

        String token = jwtConfig.generateToken(user.getEmail());

        return new AuthResponseUser(token, user.getNama(), user.getEmail(), "Login berhasil");
    }

    public User register(RegisterRequestUser request) {
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

        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User updateUser(Long id, RegisterRequestUser request) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User tidak ditemukan"));

        if (!user.getEmail().equals(request.getEmail()) && userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email sudah digunakan");
        }

        if (!user.getNoTelpon().equals(request.getNoTelpon()) && userRepository.existsByNoTelpon(request.getNoTelpon())) {
            throw new RuntimeException("No telepon sudah digunakan");
        }

        user.setNama(request.getNama());
        user.setEmail(request.getEmail());
        user.setNoTelpon(request.getNoTelpon());
        if (request.getPassword() != null && !request.getPassword().isEmpty()) {
            if (!request.getPassword().equals(request.getConfirmPassword())) {
                throw new RuntimeException("Password dan konfirmasi password tidak sama");
            }
            user.setPassword(passwordEncoder.encode(request.getPassword()));
        }

        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User tidak ditemukan"));
        userRepository.delete(user);
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
