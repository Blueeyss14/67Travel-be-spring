package com.example._travel_be.features.vehicle.dto;

import lombok.Data;

@Data
public class VehicleRequest {
    private String name;
    private Double price;
    private Integer maxPassenger;
}
