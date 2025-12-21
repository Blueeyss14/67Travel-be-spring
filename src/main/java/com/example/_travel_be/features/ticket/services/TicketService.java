package com.example._travel_be.features.ticket.services;

import com.example._travel_be.features.ticket.dto.*;
import com.example._travel_be.features.ticket.model.Ticket;
import com.example._travel_be.features.ticket.repository.TicketRepository;
import com.example._travel_be.features.destination.repository.DestinationRepository;
import com.example._travel_be.features.vehicle.repository.VehicleRepository;
import com.example._travel_be.features.accommodation.repository.AccommodationRepository;
import com.example._travel_be.features.auth.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class TicketService {

    private final TicketRepository repo;
    private final DestinationRepository destinationRepo;
    private final VehicleRepository vehicleRepo;
    private final AccommodationRepository accommodationRepo;
    private final UserRepository userRepo;

    public TicketService(
            TicketRepository repo,
            DestinationRepository destinationRepo,
            VehicleRepository vehicleRepo,
            AccommodationRepository accommodationRepo,
            UserRepository userRepo
    ) {
        this.repo = repo;
        this.destinationRepo = destinationRepo;
        this.vehicleRepo = vehicleRepo;
        this.accommodationRepo = accommodationRepo;
        this.userRepo = userRepo;
    }

    public TicketResponse create(TicketRequest req, Long userId) {

        var user = userRepo.findById(userId).orElseThrow();
        var destination = destinationRepo.findById(req.getDestination_id()).orElseThrow();
        var vehicle = vehicleRepo.findById(req.getVehicle_id()).orElseThrow();
        var accommodation = accommodationRepo.findById(req.getAccommodation_id()).orElseThrow();

        double total =
                destination.getPrice() * req.getGuest_count()
                        + vehicle.getPrice()
                        + accommodation.getPrice();

        Ticket t = new Ticket();
        t.setTicketCode(UUID.randomUUID().toString().substring(0, 10).toUpperCase());
        t.setUser(user);
        t.setDestination(destination);
        t.setVehicle(vehicle);
        t.setAccommodation(accommodation);
        t.setGuestCount(req.getGuest_count());
        t.setTotalPrice(total);
        t.setExpiredAt(LocalDateTime.parse(req.getExpired_at().replace(" ", "T")));

        repo.save(t);

        return mapResponse(t);
    }

    public List<TicketResponse> getAll(Long userId) {
        return repo.findByUserId(userId).stream()
                .map(this::mapResponse)
                .toList();
    }

    public void delete(Long id, Long userId) {
        Ticket t = repo.findById(id).orElseThrow();
        if (!t.getUser().getId().equals(userId)) {
            throw new RuntimeException("forbidden");
        }
        repo.delete(t);
    }

    private TicketResponse mapResponse(Ticket t) {
        return TicketResponse.builder()
                .ticket_code(t.getTicketCode())
                .destination_name(t.getDestination().getName())
                .vehicle_name(t.getVehicle().getName())
                .accommodation_name(t.getAccommodation().getName())
                .location(t.getDestination().getLocation())
                .expired_at(t.getExpiredAt())
                .guest_count(t.getGuestCount())
                .total_price(t.getTotalPrice())
                .created_at(t.getCreatedAt())
                .price_breakdown(List.of(
                        PriceBreakdown.builder()
                                .name(t.getDestination().getName())
                                .type("destination")
                                .total(t.getDestination().getPrice() * t.getGuestCount())
                                .detail_price(
                                        t.getGuestCount() + " x " + t.getDestination().getPrice()
                                ).build(),
                        PriceBreakdown.builder()
                                .name(t.getVehicle().getName())
                                .type("vehicle")
                                .total(t.getVehicle().getPrice())
                                .detail_price("1 x " + t.getVehicle().getPrice())
                                .build(),
                        PriceBreakdown.builder()
                                .name(t.getAccommodation().getName())
                                .type("accommodation")
                                .total(t.getAccommodation().getPrice())
                                .detail_price("1 x " + t.getAccommodation().getPrice())
                                .build()
                ))
                .build();
    }
}
