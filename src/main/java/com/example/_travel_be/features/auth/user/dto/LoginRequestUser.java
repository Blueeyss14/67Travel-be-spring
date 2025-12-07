package com.example._travel_be.features.auth.user.dto;

import lombok.Data;

@Data
public class LoginRequestUser {
    private String email;
    private String password;
}
