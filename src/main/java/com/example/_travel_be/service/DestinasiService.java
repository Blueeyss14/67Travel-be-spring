package com.example._travel_be.service;

import com.example._travel_be.model.Destinasi;
import com.example._travel_be.repository.DestinasiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DestinasiService {

    private final DestinasiRepository destinasiRepository;

    @Autowired
    public DestinasiService(DestinasiRepository destinasiRepository) {
        this.destinasiRepository = destinasiRepository;
    }

    // Get all destinasi
    public List<Destinasi> getAllDestinasi() {
        return destinasiRepository.findAll();
    }

    // Get destinasi by id
    public Optional<Destinasi> getDestinasiById(Integer id) {
        return destinasiRepository.findById(id);
    }

    // Create new destinasi
    public Destinasi createDestinasi(Destinasi destinasi) {
        // Logika Bisnis: Validasi simple
        if (destinasi.getNama() == null || destinasi.getNama().isEmpty()) {
            throw new IllegalArgumentException("Nama destinasi tidak boleh kosong.");
        }
        if (destinasi.getHarga() < 0) {
            destinasi.setHarga(0.0);
        }
        return destinasiRepository.save(destinasi);
    }

    // Update destinasi
    public Destinasi updateDestinasi(Integer id, Destinasi destinasiDetails) {
        Destinasi destinasi = destinasiRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Destinasi tidak ditemukan dengan id: " + id));

        destinasi.setNama(destinasiDetails.getNama());
        destinasi.setLokasi(destinasiDetails.getLokasi());
        destinasi.setDeskripsi(destinasiDetails.getDeskripsi());
        destinasi.setHarga(destinasiDetails.getHarga());
        destinasi.setRating(destinasiDetails.getRating());
        destinasi.setMaxGuest(destinasiDetails.getMaxGuest());

        return destinasiRepository.save(destinasi);
    }

    // Delete destinasi
    public void deleteDestinasi(Integer id) {
        Destinasi destinasi = destinasiRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Destinasi tidak ditemukan dengan id: " + id));
        destinasiRepository.delete(destinasi);
    }

    // Get destinasi by lokasi
    public List<Destinasi> getDestinasiByLokasi(String lokasi) {
        return destinasiRepository.findByLokasi(lokasi);
    }

    // Tampilkan detail (logging only, mimicking TransportasiService)
    public void tampilkanDetail(Integer id) {
        Optional<Destinasi> dOpt = destinasiRepository.findById(id);
        if (dOpt.isPresent()) {
            Destinasi d = dOpt.get();
            System.out.println("Destinasi: " + d.getNama());
            System.out.println("Lokasi: " + d.getLokasi());
            System.out.println("Deskripsi: " + d.getDeskripsi());
            System.out.println("Harga: " + d.getHarga());
            System.out.println("Rating: " + d.getRating());
        } else {
            System.out.println("Destinasi tidak ditemukan ID: " + id);
        }
    }
}