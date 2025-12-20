package com.example._travel_be.features.destination.controller;

import com.example._travel_be.features.destination.services.DestinationService;
import com.example._travel_be.features.destination.dto.DestinationRequest;
import com.example._travel_be.features.destination.model.Destination;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/destinations")
@CrossOrigin("*")
public class DestinationController {

    private final DestinationService service;

    public DestinationController(DestinationService service) {
        this.service = service;
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Destination> create(
            @RequestParam("name") String name,
            @RequestParam("location") String location,
            @RequestParam("owner") String owner,
            @RequestParam("maxOfGuest") Integer maxOfGuest,
            @RequestParam("price") Double price,
            @RequestParam("thumbnailUrl") MultipartFile thumbnail,
            @RequestParam(value = "imageUrls[]", required = false) List<MultipartFile> images,
            @RequestParam(value = "description", required = false) String description,
            @RequestParam(value = "facilities[]", required = false) List<String> facilities,
            Authentication authentication
    ) throws Exception {

        Long adminId = (Long) authentication.getPrincipal();

        DestinationRequest req = new DestinationRequest();
        req.setName(name);
        req.setLocation(location);
        req.setOwner(owner);
        req.setMaxOfGuest(maxOfGuest);
        req.setPrice(price);
        req.setDescription(description);
        req.setFacilities(facilities);

        Destination d = service.create(req, thumbnail, images, adminId);
        return ResponseEntity.ok(d);
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            Authentication authentication
    ) {
        Long userId = authentication != null ? (Long) authentication.getPrincipal() : null;
        Page<Destination> data = service.getAll(PageRequest.of(page, size), userId);
        return ResponseEntity.ok(Map.of("content", data.getContent()));
    }

    @GetMapping("/{id}")
    public Destination get(@PathVariable Long id) {
        return service.get(id);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Destination> update(
            @PathVariable Long id,
            @RequestParam("name") String name,
            @RequestParam("location") String location,
            @RequestParam("owner") String owner,
            @RequestParam("maxOfGuest") Integer maxOfGuest,
            @RequestParam("price") Double price,
            @RequestParam(value = "thumbnailUrl", required = false) MultipartFile thumbnail,
            @RequestParam(value = "imageUrls[]", required = false) List<MultipartFile> images,
            @RequestParam(value = "description", required = false) String description,
            @RequestParam(value = "facilities[]", required = false) List<String> facilities,
            Authentication authentication
    ) throws Exception {

        Long adminId = (Long) authentication.getPrincipal();

        DestinationRequest req = new DestinationRequest();
        req.setName(name);
        req.setLocation(location);
        req.setOwner(owner);
        req.setMaxOfGuest(maxOfGuest);
        req.setPrice(price);
        req.setDescription(description);
        req.setFacilities(facilities);

        Destination d = service.update(id, req, thumbnail, images, adminId);
        return ResponseEntity.ok(d);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id, Authentication authentication) {
        Long adminId = (Long) authentication.getPrincipal();
        service.delete(id, adminId);
        return ResponseEntity.ok(Map.of("message", "deleted"));
    }

    @PutMapping("/{id}/toggle-bookmark")
    public ResponseEntity<Destination> toggleBookmark(
            @PathVariable Long id,
            Authentication authentication
    ) {
        Long userId = (Long) authentication.getPrincipal();
        Destination d = service.toggleBookmark(id, userId);
        return ResponseEntity.ok(d);
    }
}
