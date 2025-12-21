package com.example._travel_be.features.ticket.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PriceBreakdown {
    private String name;
    private String type;
    private Double total;
    private String detail_price;
}
