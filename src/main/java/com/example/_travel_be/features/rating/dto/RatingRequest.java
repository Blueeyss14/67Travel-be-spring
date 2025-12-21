package com.example._travel_be.features.rating.dto;

import lombok.Data;

@Data
public class RatingRequest {
    private Integer rate;
    private String description;
}
