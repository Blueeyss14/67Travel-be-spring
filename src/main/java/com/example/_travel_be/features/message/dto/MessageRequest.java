package com.example._travel_be.features.message.dto;

import lombok.Data;

@Data
public class MessageRequest {
    private Long userId;
    private String userMessage;
    private String adminMessage;
}
