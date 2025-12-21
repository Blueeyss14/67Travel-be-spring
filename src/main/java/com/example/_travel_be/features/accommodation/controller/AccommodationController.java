package com.example._travel_be.features.accommodation.controller;

import com.example._travel_be.features.accommodation.model.Accommodation;
import com.example._travel_be.features.accommodation.services.AccommodationService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
@RequestMapping("/api/accommodations")
@CrossOrigin("*")
public class AccommodationController {

    private final AccommodationService service;

    public AccommodationController(AccommodationService service) {
        this.service = service;
    }

    @PostMapping(consumes = "multipart/form-data")
    public ResponseEntity<?> create(
            @RequestParam String name,
            @RequestParam Double latitude,
            @RequestParam Double longitude,
            @RequestParam Double price,
            @RequestParam(required = false) MultipartFile thumbnail
    ) {
        return ResponseEntity.ok(
                service.create(name, latitude, longitude, price, thumbnail)
        );
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

    @PutMapping(value = "/{id}", consumes = "multipart/form-data")
    public ResponseEntity<?> update(
            @PathVariable Long id,
            @RequestParam String name,
            @RequestParam Double latitude,
            @RequestParam Double longitude,
            @RequestParam Double price,
            @RequestParam(required = false) MultipartFile thumbnail
    ) {
        return ResponseEntity.ok(
                service.update(id, name, latitude, longitude, price, thumbnail)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok(Map.of("message", "deleted"));
    }
}
