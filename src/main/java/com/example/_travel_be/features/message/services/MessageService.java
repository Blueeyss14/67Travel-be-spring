package com.example._travel_be.features.message.services;

import com.example._travel_be.features.auth.admin.repository.AdminRepository;
import com.example._travel_be.features.auth.user.repository.UserRepository;
import com.example._travel_be.features.message.dto.ChatResponseDTO;
import com.example._travel_be.features.message.model.Message;
import com.example._travel_be.features.message.repository.MessageRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MessageService {

    private final MessageRepository repo;
    private final UserRepository userRepo;
    private final AdminRepository adminRepo;

    public MessageService(MessageRepository repo, UserRepository userRepo, AdminRepository adminRepo) {
        this.repo = repo;
        this.userRepo = userRepo;
        this.adminRepo = adminRepo;
    }

    public Message sendMessage(String senderType, Long senderId, String receiverType, Long receiverId, String content) {
        Message msg = new Message();
        msg.setSenderType(senderType);
        msg.setSenderId(senderId);
        msg.setReceiverType(receiverType);
        msg.setReceiverId(receiverId);
        msg.setContent(content);
        return repo.save(msg);
    }

    public List<ChatResponseDTO> getChat(String requesterType, Long requesterId, String targetType, Long targetId) {
        List<Message> messages1 = repo.findBySenderTypeAndSenderIdAndReceiverTypeAndReceiverId(
                requesterType, requesterId, targetType, targetId
        );
        List<Message> messages2 = repo.findBySenderTypeAndSenderIdAndReceiverTypeAndReceiverId(
                targetType, targetId, requesterType, requesterId
        );
        messages1.addAll(messages2);
        messages1.sort((a,b) -> a.getTimestamp().compareTo(b.getTimestamp()));

        List<ChatResponseDTO> response = new ArrayList<>();
        for(Message msg : messages1) {
            String senderName = "";
            if(msg.getSenderType().equals("USER")) {
                senderName = userRepo.findById(msg.getSenderId())
                        .map(u -> u.getNama())
                        .orElse("Unknown User");
            } else {
                senderName = adminRepo.findById(msg.getSenderId())
                        .map(a -> a.getUsername())
                        .orElse("Unknown Admin");
            }
            response.add(new ChatResponseDTO(
                    msg.getId(),
                    senderName,
                    msg.getContent(),
                    msg.getTimestamp()
            ));
        }

        return response;
    }
}