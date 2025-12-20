package com.example._travel_be.features.destination.dto;

import lombok.Data;

import java.util.List;

@Data
public class DestinationRequest {
    private String name;
    private String location;
    private String owner;
    private Integer maxOfGuest;
    private Double price;
    private List<String> facilities;
    private String description;
}
