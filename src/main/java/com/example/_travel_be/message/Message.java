package com.example._travel_be.message.model;

import jakarta.persistence.*;

@Entity
@Table(name = "messages")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;
    private String userName;
    private String userMessage;
    private String adminMessage;
    private Long timestamp;

    public Message() {}

    // ===== GETTER =====
    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserMessage() {
        return userMessage;
    }

    public String getAdminMessage() {
        return adminMessage;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    // ===== SETTER =====
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserMessage(String userMessage) {
        this.userMessage = userMessage;
    }

    public void setAdminMessage(String adminMessage) {
        this.adminMessage = adminMessage;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }
}
