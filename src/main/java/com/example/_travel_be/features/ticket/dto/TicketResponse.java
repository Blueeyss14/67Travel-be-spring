package com.example._travel_be.features.ticket.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class TicketResponse {

    private String ticket_code;
    private String destination_name;
    private String vehicle_name;
    private String accommodation_name;
    private String location;
    private LocalDateTime expired_at;
    private Integer guest_count;
    private Double total_price;
    private List<PriceBreakdown> price_breakdown;
    private LocalDateTime created_at;
}
