package com.example._travel_be.message.dto;


public class ChatDTO {

    private Long userId;
    private String userName;
    private String userMessage;
    private String adminMessage;
    private Long timestamp;

    public ChatDTO() {}

    public ChatDTO(Long userId, String userName, String userMessage, String adminMessage, Long timestamp) {
        this.userId = userId;
        this.userName = userName;
        this.userMessage = userMessage;
        this.adminMessage = adminMessage;
        this.timestamp = timestamp;
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
}
