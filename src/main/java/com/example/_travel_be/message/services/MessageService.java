package com.example._travel_be.message.services;

import com.example._travel_be.message.model.Message;
import com.example._travel_be.message.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    public Message saveUserMessage(Long userId, String userName, String message) {
        Message msg = new Message();
        msg.setUserId(userId);
        msg.setUserName(userName);
        msg.setUserMessage(message);
        msg.setTimestamp(System.currentTimeMillis());
        return messageRepository.save(msg);
    }

    public List<Message> getMessagesByUser(Long userId) {
        return messageRepository.findByUserId(userId);
    }
}
