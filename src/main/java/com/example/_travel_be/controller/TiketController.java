package com.example._travel_be.controller;

import com.example._travel_be.model.Tiket;
import com.example._travel_be.service.TiketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID; // Unused but kept cleanly or removed

@RestController
@RequestMapping("/api/tiket")
public class TiketController {

    @Autowired
    private TiketService tiketService;

    @GetMapping
    public List<Tiket> getAllTiket() {
        return tiketService.getAllTiket();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tiket> getTiketById(@PathVariable Integer id) {
        return tiketService.getTiketById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Tiket createTiket(@RequestBody Tiket tiket) {
        return tiketService.createTiket(tiket);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTiket(@PathVariable Integer id) {
        tiketService.deleteTiket(id);
        return ResponseEntity.noContent().build();
    }
}
