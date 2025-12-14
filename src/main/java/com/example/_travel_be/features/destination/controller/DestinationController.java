package com.example._travel_be.features.destination.controller;

import com.example._travel_be.features.destination.services.DestinationService;
import com.example._travel_be.features.destination.dto.DestinationRequest;
import com.example._travel_be.features.destination.model.Destination;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.fasterxml.jackson.databind.ObjectMapper;


import java.io.File;
import java.util.ArrayList;
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
    public ResponseEntity<?> create(
            @RequestPart("data") String data,
            @RequestPart("thumbnail") MultipartFile thumbnail,
            @RequestPart(value = "images", required = false) List<MultipartFile> images
    ) throws Exception {

        ObjectMapper mapper = new ObjectMapper();
        DestinationRequest req = mapper.readValue(data, DestinationRequest.class);

        String uploadDir = System.getProperty("user.dir") + "/uploads/";
        File dir = new File(uploadDir);
        if (!dir.exists()) dir.mkdirs();

        String thumbnailName = System.currentTimeMillis() + "_" + thumbnail.getOriginalFilename();
        File thumbnailFile = new File(uploadDir + thumbnailName);
        thumbnail.transferTo(thumbnailFile);
        String thumbnailUrl = "/uploads/" + thumbnailName;

        List<String> imageUrls = new ArrayList<>();
        if (images != null) {
            for (MultipartFile img : images) {
                String imgName = System.currentTimeMillis() + "_" + img.getOriginalFilename();
                File imgFile = new File(uploadDir + imgName);
                img.transferTo(imgFile);
                imageUrls.add("/uploads/" + imgName);
            }
        }

        return ResponseEntity.ok(service.create(req, thumbnailUrl, imageUrls));
    }


    @GetMapping
    public Page<Destination> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return service.getAll(PageRequest.of(page, size));
    }

    @GetMapping("/{id}")
    public Destination get(@PathVariable Long id) {
        return service.get(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok(Map.of("message", "deleted"));
    }
}
