package com.example._travel_be.features.message.repository;

import com.example._travel_be.features.message.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findAllByUserIdOrderBySequenceAsc(Long userId);
}
