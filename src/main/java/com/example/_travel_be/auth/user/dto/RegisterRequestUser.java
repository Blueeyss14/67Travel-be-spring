package com.example._travel_be.auth.user.dto;

import lombok.Data;

@Data
public class RegisterRequestUser {
    private String nama;
    private String email;
    private String noTelpon;
    private String password;
    private String confirmPassword;
}
