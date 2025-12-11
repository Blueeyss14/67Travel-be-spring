package com.example._travel_be.service;

import com.example._travel_be.model.Destinasi;
import com.example._travel_be.repository.DestinasiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DestinasiService {

    private final DestinasiRepository destinasiRepository;

    @Autowired
    public DestinasiService(DestinasiRepository destinasiRepository) {
        this.destinasiRepository = destinasiRepository;
    }

    // --- C (CREATE) / U (UPDATE) ---
    public Destinasi save(Destinasi destinasi) {
        // Logika Bisnis: Contoh validasi data sebelum disimpan
        if (destinasi.getNama() == null || destinasi.getNama().isEmpty()) {
            throw new IllegalArgumentException("Nama destinasi tidak boleh kosong.");
        }
        if (destinasi.getHarga() < 0) {
            destinasi.setHarga(0.0); // Atur harga menjadi 0 jika diinput negatif
        }
        
        return destinasiRepository.save(destinasi);
    }

    // --- R (READ) ---
    public List<Destinasi> findAll() {
        return destinasiRepository.findAll();
    }

    public Optional<Destinasi> findById(UUID id) {
        return destinasiRepository.findById(id);
    }
    
    public List<Destinasi> findByLokasi(String lokasi) {
        return destinasiRepository.findByLokasi(lokasi);
    }

    // --- D (DELETE) ---
    public void deleteById(UUID id) {
        // Logika Bisnis: Cek dulu apakah ada keterkaitan dengan entitas lain (misalnya Tiket)
        // Jika tidak ada masalah, baru dihapus:
        destinasiRepository.deleteById(id);
    }
}