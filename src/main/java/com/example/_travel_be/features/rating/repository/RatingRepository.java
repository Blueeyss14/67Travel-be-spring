package com.example._travel_be.features.rating.repository;

import com.example._travel_be.features.rating.model.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RatingRepository extends JpaRepository<Rating, Long> {
    List<Rating> findByDestinationId(Long destinationId);
}
