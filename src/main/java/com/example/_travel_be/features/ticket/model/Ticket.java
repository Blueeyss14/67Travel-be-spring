package com.example._travel_be.features.ticket.model;

import com.example._travel_be.features.auth.user.model.User;
import com.example._travel_be.features.destination.model.Destination;
import com.example._travel_be.features.vehicle.model.Vehicle;
import com.example._travel_be.features.accommodation.model.Accommodation;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "tickets")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ticketCode;

    @ManyToOne
    private User user;

    @ManyToOne
    private Destination destination;

    @ManyToOne
    private Vehicle vehicle;

    @ManyToOne
    private Accommodation accommodation;

    private Integer guestCount;
    private Double totalPrice;
    private LocalDateTime expiredAt;

    private LocalDateTime createdAt = LocalDateTime.now();
}
