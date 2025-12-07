package com.example._travel_be.features.message.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "messages")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String senderType;
    private Long senderId;
    private String receiverType;
    private Long receiverId;
    private String content;
    private LocalDateTime timestamp = LocalDateTime.now();
}