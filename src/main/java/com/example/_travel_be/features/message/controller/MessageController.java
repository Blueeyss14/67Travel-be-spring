package com.example._travel_be.features.message.controller;

import com.example._travel_be.features.message.model.Message;
import com.example._travel_be.features.message.dto.ChatDTO;
import com.example._travel_be.features.message.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/message")
@CrossOrigin(origins = "*")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @PostMapping("/send")
    public ResponseEntity<?> sendMessage(@RequestBody Message msg) {
        try {
            Message saved = messageService.sendMessage(msg);
            return ResponseEntity.ok(saved);
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(Map.of("message", e.getMessage()));
        }
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getUserChats(@PathVariable Long userId) {
        try {
            List<ChatDTO> chats = messageService.getUserChats(userId);
            return ResponseEntity.ok(chats);
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(Map.of("message", e.getMessage()));
        }
    }
}
