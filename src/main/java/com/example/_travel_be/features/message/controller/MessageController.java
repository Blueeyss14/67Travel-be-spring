package com.example._travel_be.features.message.controller;

import com.example._travel_be.config.JwtUtils;
import com.example._travel_be.features.message.dto.ChatResponseDTO;
import com.example._travel_be.features.message.model.Message;
import com.example._travel_be.features.message.services.MessageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/messages")
@CrossOrigin(origins = "*")
public class MessageController {

    private final MessageService service;
    private final JwtUtils jwtUtils;

    public MessageController(MessageService service, JwtUtils jwtUtils) {
        this.service = service;
        this.jwtUtils = jwtUtils;
    }

    @PostMapping("/send")
    public ResponseEntity<Message> sendMessage(
            @RequestBody Map<String, String> body,
            @RequestHeader("Authorization") String token
    ) {
        String senderType = jwtUtils.getRoleFromToken(token).equals("ADMIN") ? "ADMIN" : "USER";
        Long senderId = jwtUtils.getUserIdFromToken(token);
        String receiverType = body.get("receiverType");
        Long receiverId = Long.parseLong(body.get("receiverId"));
        String content = body.get("content");
        return ResponseEntity.ok(service.sendMessage(senderType, senderId, receiverType, receiverId, content));
    }

    @GetMapping("/chat")
    public ResponseEntity<List<ChatResponseDTO>> getChat(
            @RequestParam String targetType,
            @RequestParam Long targetId,
            @RequestHeader("Authorization") String token
    ) {
        String requesterType = jwtUtils.getRoleFromToken(token).equals("ADMIN") ? "ADMIN" : "USER";
        Long requesterId = jwtUtils.getUserIdFromToken(token);
        return ResponseEntity.ok(service.getChat(requesterType, requesterId, targetType, targetId));
    }
}