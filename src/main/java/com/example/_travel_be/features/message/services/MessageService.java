package com.example._travel_be.features.message.services;

import com.example._travel_be.features.auth.user.repository.UserRepository;
import com.example._travel_be.features.message.model.Message;
import com.example._travel_be.features.message.repository.MessageRepository;
import com.example._travel_be.features.message.dto.ChatDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private UserRepository userRepository;

    public Message sendMessage(Message msg) {
        if (!userRepository.existsById(msg.getUserId())) {
            throw new RuntimeException("no user found");
        }

        Long lastSeq = messageRepository.findAllByUserIdOrderBySequenceAsc(msg.getUserId())
                .stream()
                .mapToLong(Message::getSequence)
                .max()
                .orElse(0L);
        msg.setSequence(lastSeq + 1);
        msg.setTimestamp(System.currentTimeMillis());
        return messageRepository.save(msg);
    }

    public List<ChatDTO> getUserChats(Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new RuntimeException("no user found");
        }

        String userName = userRepository.findById(userId).get().getNama();

        List<Message> msgs = messageRepository.findAllByUserIdOrderBySequenceAsc(userId);
        List<ChatDTO> chats = new ArrayList<>();
        ChatDTO current = null;

        for (Message m : msgs) {
            if ((m.getUserMessage() != null && !m.getUserMessage().isEmpty())
                    || (m.getAdminMessage() != null && !m.getAdminMessage().isEmpty())) {
                ChatDTO chat = new ChatDTO(
                        m.getId(),
                        userId,
                        userName,
                        m.getUserMessage() != null ? m.getUserMessage() : "",
                        m.getAdminMessage() != null ? m.getAdminMessage() : "",
                        m.getTimestamp() != null ? m.getTimestamp() : System.currentTimeMillis()
                );
                chats.add(chat);
            }
        }


        return chats;
    }
}
