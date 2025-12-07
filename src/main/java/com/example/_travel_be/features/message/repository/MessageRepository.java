package com.example._travel_be.features.message.repository;

import com.example._travel_be.features.message.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findBySenderTypeAndSenderIdAndReceiverTypeAndReceiverId(
            String senderType, Long senderId, String receiverType, Long receiverId
    );
}