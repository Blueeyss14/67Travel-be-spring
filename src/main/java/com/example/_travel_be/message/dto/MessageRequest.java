package com.example._travel_be.message.dto;

public class MessageRequest {

    private Long userId;
    private String userName;
    private String message;

    public MessageRequest() {}

    public Long getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getMessage() {
        return message;
    }
}
