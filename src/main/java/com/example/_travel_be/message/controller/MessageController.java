package com.example._travel_be.message.controller;

import com.example._travel_be.message.dto.ChatDTO;
import com.example._travel_be.message.dto.MessageRequest;
import com.example._travel_be.message.model.Message;
import com.example._travel_be.message.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/message")
@CrossOrigin(origins = "*")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @PostMapping
    public Message sendMessage(@RequestBody MessageRequest request) {
        return messageService.saveUserMessage(
                request.getUserId(),
                request.getUserName(),
                request.getMessage()
        );
    }

    @GetMapping("/{userId}")
    public List<ChatDTO> getChat(@PathVariable Long userId) {
        List<Message> messages = messageService.getMessagesByUser(userId);
        List<ChatDTO> chats = new ArrayList<>();

        for (Message m : messages) {
            chats.add(new ChatDTO(
                    m.getUserId(),
                    m.getUserName(),
                    m.getUserMessage(),
                    m.getAdminMessage(),
                    m.getTimestamp()
            ));
        }
        return chats;
    }
}
