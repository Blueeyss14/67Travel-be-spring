package com.example._travel_be.auth.admin.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AdminResponse {
    private String token;
    private String username;
    private String email;
    private String message;
}
