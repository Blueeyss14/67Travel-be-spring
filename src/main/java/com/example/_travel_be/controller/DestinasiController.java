package com.example._travel_be.controller;

import com.example._travel_be.model.Destinasi;
import com.example._travel_be.service.DestinasiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/destinasi")
@CrossOrigin(origins = "*")
public class DestinasiController {

    private final DestinasiService destinasiService;

    @Autowired
    public DestinasiController(DestinasiService destinasiService) {
        this.destinasiService = destinasiService;
    }

    // GET all destinasi
    @GetMapping
    public ResponseEntity<List<Destinasi>> getAllDestinasi() {
        try {
            List<Destinasi> destinasiList = destinasiService.getAllDestinasi();
            if (destinasiList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(destinasiList, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // GET destinasi by id
    @GetMapping("/{id}")
    public ResponseEntity<Destinasi> getDestinasiById(@PathVariable Integer id) {
        try {
            Optional<Destinasi> destinasi = destinasiService.getDestinasiById(id);
            return destinasi.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // GET search destinasi by lokasi
    @GetMapping("/search")
    public ResponseEntity<List<Destinasi>> getDestinasiByLokasi(@RequestParam String lokasi) {
        try {
            List<Destinasi> destinasiList = destinasiService.getDestinasiByLokasi(lokasi);
            if (destinasiList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(destinasiList, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // POST create new destinasi
    @PostMapping
    public ResponseEntity<Destinasi> createDestinasi(@RequestBody Destinasi destinasi) {
        try {
            Destinasi newDestinasi = destinasiService.createDestinasi(destinasi);
            return new ResponseEntity<>(newDestinasi, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // PUT update destinasi
    @PutMapping("/{id}")
    public ResponseEntity<Destinasi> updateDestinasi(@PathVariable Integer id,
            @RequestBody Destinasi destinasiDetails) {
        try {
            Destinasi updatedDestinasi = destinasiService.updateDestinasi(id, destinasiDetails);
            return new ResponseEntity<>(updatedDestinasi, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // DELETE destinasi
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteDestinasi(@PathVariable Integer id) {
        try {
            destinasiService.deleteDestinasi(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // GET detail (mimicking TransportasiController style)
    @GetMapping("/{id}/detail")
    public ResponseEntity<Void> tampilkanDetail(@PathVariable Integer id) {
        destinasiService.tampilkanDetail(id);
        return ResponseEntity.ok().build();
    }
}