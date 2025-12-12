package com.example._travel_be.controller;

import com.example._travel_be.model.Transportasi;
import com.example._travel_be.service.TransportasiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/transportasi")
@CrossOrigin(origins = "*")
public class TransportasiController {

    @Autowired
    private TransportasiService transportasiService;

    // GET all transportasi
    @GetMapping
    public ResponseEntity<List<Transportasi>> getAllTransportasi() {
        try {
            List<Transportasi> transportasiList = transportasiService.getAllTransportasi();
            if (transportasiList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(transportasiList, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // GET transportasi by id
    @GetMapping("/{id}")
    public ResponseEntity<Transportasi> getTransportasiById(@PathVariable("id") UUID id) {
        try {
            Optional<Transportasi> transportasiData = transportasiService.getTransportasiById(id);
            return transportasiData.map(transportasi -> new ResponseEntity<>(transportasi, HttpStatus.OK))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // POST create new transportasi
    @PostMapping
    public ResponseEntity<Transportasi> createTransportasi(@RequestBody Transportasi transportasi) {
        try {
            Transportasi newTransportasi = transportasiService.createTransportasi(transportasi);
            return new ResponseEntity<>(newTransportasi, HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // PUT update transportasi
    @PutMapping("/{id}")
    public ResponseEntity<Transportasi> updateTransportasi(
            @PathVariable("id") UUID id,
            @RequestBody Transportasi transportasi) {
        try {
            Transportasi updatedTransportasi = transportasiService.updateTransportasi(id, transportasi);
            return new ResponseEntity<>(updatedTransportasi, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // DELETE transportasi
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteTransportasi(@PathVariable("id") UUID id) {
        try {
            transportasiService.deleteTransportasi(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // GET transportasi by jenis kendaraan
    @GetMapping("/jenis/{jenisKendaraan}")
    public ResponseEntity<List<Transportasi>> getTransportasiByJenisKendaraan(
            @PathVariable("jenisKendaraan") String jenisKendaraan) {
        try {
            List<Transportasi> transportasiList = transportasiService.getTransportasiByJenisKendaraan(jenisKendaraan);
            if (transportasiList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(transportasiList, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // GET search transportasi by name
    @GetMapping("/search")
    public ResponseEntity<List<Transportasi>> searchTransportasiByName(
            @RequestParam("name") String name) {
        try {
            List<Transportasi> transportasiList = transportasiService.searchTransportasiByName(name);
            if (transportasiList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(transportasiList, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}