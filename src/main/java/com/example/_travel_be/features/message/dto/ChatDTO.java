package com.example._travel_be.features.message.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ChatDTO {
    private Long id;
    private Long userId;
    private String userName;
    private String userMessage;
    private String adminMessage;
    private Long timestamp;
}
