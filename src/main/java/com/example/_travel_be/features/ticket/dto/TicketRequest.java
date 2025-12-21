package com.example._travel_be.features.ticket.dto;

import lombok.Data;

@Data
public class TicketRequest {
    private Long destination_id;
    private Long vehicle_id;
    private Long accommodation_id;
    private Integer guest_count;
    private String expired_at;
}
