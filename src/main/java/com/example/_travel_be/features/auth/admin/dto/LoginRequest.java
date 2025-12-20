package com.example._travel_be.features.auth.admin.dto;

import lombok.Data;

@Data
public class LoginRequest {
    private String username;
    private String password;
}
