package com.example._travel_be.controller;

import com.example._travel_be.model.Akomodasi;
import com.example._travel_be.service.AkomodasiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/akomodasi")
public class AkomodasiController {

    @Autowired
    private AkomodasiService akomodasiService;

    // GET: Ambil Semua Data
    @GetMapping
    public List<Akomodasi> getAllAkomodasi() {
        return akomodasiService.getAllAkomodasi();
    }

    // GET: Ambil Data per ID
    @GetMapping("/{id}")
    public ResponseEntity<Akomodasi> getAkomodasiById(@PathVariable Integer id) {
        Optional<Akomodasi> akomodasi = akomodasiService.getAkomodasiById(id);

        // Kalau ada return data, kalau gak ada return 404 Not Found
        return akomodasi.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // POST: Tambah Data Baru
    @PostMapping
    public Akomodasi createAkomodasi(@RequestBody Akomodasi akomodasi) {
        return akomodasiService.createAkomodasi(akomodasi);
    }

    // PUT: Update Data
    @PutMapping("/{id}")
    public ResponseEntity<Akomodasi> updateAkomodasi(@PathVariable Integer id, @RequestBody Akomodasi akomodasi) {
        Akomodasi updated = akomodasiService.updateAkomodasi(id, akomodasi);

        if (updated != null) {
            return ResponseEntity.ok(updated);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // DELETE: Hapus Data
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAkomodasi(@PathVariable Integer id) {
        boolean isDeleted = akomodasiService.deleteAkomodasi(id);

        if (isDeleted) {
            return ResponseEntity.noContent().build(); // Sukses hapus (Status 204)
        } else {
            return ResponseEntity.notFound().build(); // Gagal hapus, ID gak ada
        }
    }

    @GetMapping("/{id}/detail")
    public ResponseEntity<Void> tampilkanDetail(@PathVariable Integer id) {
        akomodasiService.tampilkanDetail(id);
        return ResponseEntity.ok().build();
    }
}