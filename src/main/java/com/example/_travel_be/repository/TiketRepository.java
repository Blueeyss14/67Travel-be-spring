package com.example._travel_be.repository;

import com.example._travel_be.model.Tiket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TiketRepository extends JpaRepository<Tiket, Integer> {
}
