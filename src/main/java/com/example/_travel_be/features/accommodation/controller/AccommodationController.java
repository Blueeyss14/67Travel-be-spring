package com.example._travel_be.features.accommodation.controller;

import com.example._travel_be.features.accommodation.dto.AccommodationRequest;
import com.example._travel_be.features.accommodation.model.Accommodation;
import com.example._travel_be.features.accommodation.services.AccommodationService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/accommodations")
@CrossOrigin("*")
public class AccommodationController {

    private final AccommodationService service;

    public AccommodationController(AccommodationService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody AccommodationRequest req) {
        return ResponseEntity.ok(service.create(req));
    }

    @GetMapping
    public Page<Accommodation> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return service.getAll(PageRequest.of(page, size));
    }

    @GetMapping("/{id}")
    public Accommodation get(@PathVariable Long id) {
        return service.get(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(
            @PathVariable Long id,
            @RequestBody AccommodationRequest req
    ) {
        return ResponseEntity.ok(service.update(id, req));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok(Map.of("message", "deleted"));
    }
}
