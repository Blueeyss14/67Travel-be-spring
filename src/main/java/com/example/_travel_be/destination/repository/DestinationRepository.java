package com.example._travel_be.destination.repository;

import com.example._travel_be.destination.model.Destination;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DestinationRepository extends JpaRepository<Destination, Long> {
}
