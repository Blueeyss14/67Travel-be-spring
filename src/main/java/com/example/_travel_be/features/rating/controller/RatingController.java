package com.example._travel_be.features.rating.controller;

import com.example._travel_be.features.rating.dto.RatingRequest;
import com.example._travel_be.features.rating.services.RatingService;
import org.springframework.security.core.Authentication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/destinations")
@CrossOrigin("*")
public class RatingController {

    private final RatingService ratingService;

    public RatingController(RatingService ratingService) {
        this.ratingService = ratingService;
    }

    @PostMapping("/{id}/rating")
    public ResponseEntity<?> rate(
            @PathVariable Long id,
            @RequestBody RatingRequest req,
            Authentication authentication
    ) {
        Long userId = (Long) authentication.getPrincipal();
        return ResponseEntity.ok(ratingService.create(id, req, userId));
    }
}
