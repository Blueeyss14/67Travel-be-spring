package com.example._travel_be.features.message.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ChatResponseDTO {
    private Long id;
    private String sender;
    private String content;
    private LocalDateTime timestamp;
}