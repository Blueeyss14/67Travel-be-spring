package com.example._travel_be.jwt;

import com.example._travel_be.auth.user.model.User;
import com.example._travel_be.auth.user.repository.UserRepository;
import org.springframework.stereotype.Component;

@Component
public class JwtUtils {

    private final UserRepository userRepository;
    private final JwtConfig jwtConfig;

    public JwtUtils(UserRepository userRepository, JwtConfig jwtConfig) {
        this.userRepository = userRepository;
        this.jwtConfig = jwtConfig;
    }

    public Long getUserIdFromToken(String token) {
        String actualToken = token.replace("Bearer ", "");
        String identifier = jwtConfig.getEmailFromToken(actualToken);

        User user = userRepository.findByEmail(identifier).orElse(null);
        if(user != null) return user.getId();

        return null;
    }

    public String getRoleFromToken(String token) {
        String actualToken = token.replace("Bearer ", "");
        String identifier = jwtConfig.getEmailFromToken(actualToken);

        User user = userRepository.findByEmail(identifier).orElse(null);
        if(user != null) return "USER";

        return "USER NOT FOUND";
    }
}
