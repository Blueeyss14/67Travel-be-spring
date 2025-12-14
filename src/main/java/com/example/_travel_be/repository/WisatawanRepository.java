package com.example._travel_be.repository;

import com.example._travel_be.model.Wisatawan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface WisatawanRepository extends JpaRepository<Wisatawan, UUID> {
}
