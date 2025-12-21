package com.example._travel_be.features.accommodation.repository;

import com.example._travel_be.features.accommodation.model.Accommodation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccommodationRepository extends JpaRepository<Accommodation, Long> {
}
