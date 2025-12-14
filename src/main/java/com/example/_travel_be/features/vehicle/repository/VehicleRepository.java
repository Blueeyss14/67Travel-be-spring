package com.example._travel_be.features.vehicle.repository;

import com.example._travel_be.features.vehicle.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
}
