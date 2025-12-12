package com.example._travel_be.controller;

import com.example._travel_be.model.LaporanPengalaman;
import com.example._travel_be.service.LaporanPengalamanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/laporan-pengalaman")
@CrossOrigin(origins = "*")
public class LaporanPengalamanController {

    @Autowired
    private LaporanPengalamanService laporanPengalamanService;

    @GetMapping
    public ResponseEntity<List<LaporanPengalaman>> getAllLaporan() {
        List<LaporanPengalaman> laporanList = laporanPengalamanService.getAllLaporan();
        return new ResponseEntity<>(laporanList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LaporanPengalaman> getLaporanById(@PathVariable UUID id) {
        LaporanPengalaman laporan = laporanPengalamanService.getLaporanById(id);
        if (laporan != null) {
            return new ResponseEntity<>(laporan, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/wisatawan/{wisatawanId}")
    public ResponseEntity<List<LaporanPengalaman>> getLaporanByWisatawan(@PathVariable UUID wisatawanId) {
        List<LaporanPengalaman> laporanList = laporanPengalamanService.getLaporanByWisatawanId(wisatawanId);
        return new ResponseEntity<>(laporanList, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<LaporanPengalaman> createLaporan(@RequestBody LaporanPengalaman laporanPengalaman) {
        LaporanPengalaman savedLaporan = laporanPengalamanService.createLaporan(laporanPengalaman);
        return new ResponseEntity<>(savedLaporan, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LaporanPengalaman> updateLaporan(
            @PathVariable UUID id,
            @RequestBody LaporanPengalaman laporanPengalaman) {
        LaporanPengalaman updatedLaporan = laporanPengalamanService.updateLaporan(id, laporanPengalaman);
        if (updatedLaporan != null) {
            return new ResponseEntity<>(updatedLaporan, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLaporan(@PathVariable UUID id) {
        boolean deleted = laporanPengalamanService.deleteLaporan(id);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}