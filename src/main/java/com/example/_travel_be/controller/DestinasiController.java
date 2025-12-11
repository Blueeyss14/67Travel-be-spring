package com.example._travel_be.controller;

import com.example._travel_be.model.Destinasi;
import com.example._travel_be.service.DestinasiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/destinasi")
public class DestinasiController {

    private final DestinasiService destinasiService;

    // INJEKSI (Dependency Injection) DestinasiService
    @Autowired
    public DestinasiController(DestinasiService destinasiService) {
        this.destinasiService = destinasiService;
    }

    // 1. GET ALL: /api/destinasi
    @GetMapping
    public ResponseEntity<List<Destinasi>> getAllDestinasi() {
        List<Destinasi> destinasiList = destinasiService.findAll();
        return new ResponseEntity<>(destinasiList, HttpStatus.OK); 
    }

    // 2. GET BY ID: /api/destinasi/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Destinasi> getDestinasiById(@PathVariable UUID id) {
        return destinasiService.findById(id)
                .map(destinasi -> new ResponseEntity<>(destinasi, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND)); 
    }
    
    // 3. GET BY LOKASI: /api/destinasi/search?lokasi=Bali
    @GetMapping("/search")
    public ResponseEntity<List<Destinasi>> getDestinasiByLokasi(@RequestParam String lokasi) {
        List<Destinasi> destinasiList = destinasiService.findByLokasi(lokasi);
        return new ResponseEntity<>(destinasiList, HttpStatus.OK);
    }

    // 4. POST (CREATE): /api/destinasi
    @PostMapping
    public ResponseEntity<Destinasi> createDestinasi(@RequestBody Destinasi destinasi) {
        try {
            Destinasi newDestinasi = destinasiService.save(destinasi);
            // Mengembalikan objek yang baru dibuat dengan status HTTP 201 CREATED
            return new ResponseEntity<>(newDestinasi, HttpStatus.CREATED); 
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null); 
        }
    }
    
    // 5. PUT (UPDATE): /api/destinasi/{id}
    @PutMapping("/{id}")
    public ResponseEntity<Destinasi> updateDestinasi(@PathVariable UUID id, @RequestBody Destinasi destinasiDetails) {
        return destinasiService.findById(id)
                .map(existingDestinasi -> {
                    // Update properti yang dikirim dari body
                    existingDestinasi.setNama(destinasiDetails.getNama());
                    existingDestinasi.setDeskripsi(destinasiDetails.getDeskripsi());
                    existingDestinasi.setHarga(destinasiDetails.getHarga());
                    // ... lakukan update field lainnya

                    Destinasi updatedDestinasi = destinasiService.save(existingDestinasi);
                    return new ResponseEntity<>(updatedDestinasi, HttpStatus.OK);
                })
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // 6. DELETE: /api/destinasi/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteDestinasi(@PathVariable UUID id) {
        try {
            destinasiService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // Status 204
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); // Status 500
        }
    }
}