package com.example._travel_be.features.accommodation.dto;

import lombok.Data;

@Data
public class AccommodationRequest {
    private String name;
    private Double latitude;
    private Double longitude;
    private Double price;
}
