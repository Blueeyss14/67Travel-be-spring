package com.example._travel_be.message.model;

import jakarta.persistence.*;

@Entity
@Table(name = "messages")
public class MessageModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "user_message")
    private String userMessage;

    @Column(name = "admin_message")
    private String adminMessage;

    private Integer sequence;
    private Long timestamp;

    public MessageModel() {}

    // ===== GETTER =====
    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public String getUserMessage() {
        return userMessage;
    }

    public String getAdminMessage() {
        return adminMessage;
    }

    public Integer getSequence() {
        return sequence;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    // ===== SETTER =====
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setUserMessage(String userMessage) {
        this.userMessage = userMessage;
    }

    public void setAdminMessage(String adminMessage) {
        this.adminMessage = adminMessage;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }
}
