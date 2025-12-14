package com.example._travel_be.features.vehicle.controller;

import com.example._travel_be.features.vehicle.dto.VehicleRequest;
import com.example._travel_be.features.vehicle.model.Vehicle;
import com.example._travel_be.features.vehicle.services.VehicleService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Map;

@RestController
@RequestMapping("/api/vehicles")
@CrossOrigin("*")
public class VehicleController {

    private final VehicleService service;

    public VehicleController(VehicleService service) {
        this.service = service;
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> create(
            @RequestPart("data") String data,
            @RequestPart("thumbnail") MultipartFile thumbnail
    ) throws Exception {

        ObjectMapper mapper = new ObjectMapper();
        VehicleRequest req = mapper.readValue(data, VehicleRequest.class);

        String uploadDir = System.getProperty("user.dir") + "/uploads/";
        File dir = new File(uploadDir);
        if (!dir.exists()) dir.mkdirs();

        String name = System.currentTimeMillis() + "_" + thumbnail.getOriginalFilename();
        File file = new File(uploadDir + name);
        thumbnail.transferTo(file);

        return ResponseEntity.ok(
                service.create(req, "/uploads/" + name)
        );
    }

    @GetMapping
    public Page<Vehicle> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return service.getAll(PageRequest.of(page, size));
    }

    @GetMapping("/{id}")
    public Vehicle get(@PathVariable Long id) {
        return service.get(id);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> update(
            @PathVariable Long id,
            @RequestPart("data") String data,
            @RequestPart(value = "thumbnail", required = false) MultipartFile thumbnail
    ) throws Exception {

        ObjectMapper mapper = new ObjectMapper();
        VehicleRequest req = mapper.readValue(data, VehicleRequest.class);

        String thumbnailUrl = null;
        if (thumbnail != null) {
            String uploadDir = System.getProperty("user.dir") + "/uploads/";
            File dir = new File(uploadDir);
            if (!dir.exists()) dir.mkdirs();

            String name = System.currentTimeMillis() + "_" + thumbnail.getOriginalFilename();
            File file = new File(uploadDir + name);
            thumbnail.transferTo(file);
            thumbnailUrl = "/uploads/" + name;
        }

        return ResponseEntity.ok(
                service.update(id, req, thumbnailUrl)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok(Map.of("message", "deleted"));
    }
}
