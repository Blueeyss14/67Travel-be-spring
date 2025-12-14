package com.example._travel_be.controller;

import com.example._travel_be.model.LaporanPengalaman;
import com.example._travel_be.service.LaporanPengalamanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/laporan-pengalaman")
@CrossOrigin(origins = "*") // Added for consistency with other controllers
public class LaporanPengalamanController {

    private final LaporanPengalamanService laporanPengalamanService;

    @Autowired
    public LaporanPengalamanController(LaporanPengalamanService laporanPengalamanService) {
        this.laporanPengalamanService = laporanPengalamanService;
    }

    // GET ALL
    @GetMapping
    public ResponseEntity<List<LaporanPengalaman>> getAllLaporan() {
        try {
            List<LaporanPengalaman> list = laporanPengalamanService.getAllLaporan();
            if (list.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // GET BY ID
    @GetMapping("/{id}")
    public ResponseEntity<LaporanPengalaman> getLaporanById(@PathVariable Integer id) {
        try {
            Optional<LaporanPengalaman> lap = laporanPengalamanService.getLaporanById(id);
            return lap.map(l -> new ResponseEntity<>(l, HttpStatus.OK))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // CREATE
    @PostMapping
    public ResponseEntity<LaporanPengalaman> createLaporan(@RequestBody LaporanPengalaman laporan) {
        try {
            LaporanPengalaman saved = laporanPengalamanService.createLaporan(laporan);
            return new ResponseEntity<>(saved, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<LaporanPengalaman> updateLaporan(@PathVariable Integer id,
            @RequestBody LaporanPengalaman details) {
        try {
            LaporanPengalaman updated = laporanPengalamanService.updateLaporan(id, details);
            return new ResponseEntity<>(updated, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteLaporan(@PathVariable Integer id) {
        try {
            laporanPengalamanService.deleteLaporan(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}