package com.example._travel_be.features.ticket.controller;

import com.example._travel_be.features.ticket.dto.TicketRequest;
import com.example._travel_be.features.ticket.services.TicketService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tickets")
@CrossOrigin("*")
public class TicketController {

    private final TicketService service;

    public TicketController(TicketService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<?> create(
            @RequestBody TicketRequest req,
            Authentication auth
    ) {
        Long userId = Long.valueOf(auth.getName());
        return ResponseEntity.ok(service.create(req, userId));
    }

    @GetMapping
    public ResponseEntity<?> getAll(Authentication auth) {
        Long userId = Long.valueOf(auth.getName());
        return ResponseEntity.ok(service.getAll(userId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(
            @PathVariable Long id,
            Authentication auth
    ) {
        Long userId = Long.valueOf(auth.getName());
        service.delete(id, userId);
        return ResponseEntity.ok().build();
    }
}
