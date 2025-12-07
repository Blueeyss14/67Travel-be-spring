package com.example._travel_be.features.auth.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthResponseUser {
    private String token;
    private String nama;
    private String email;
    private String message;
}
