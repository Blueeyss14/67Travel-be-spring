package com.example._travel_be.service;

import com.example._travel_be.model.LaporanPengalaman;
import com.example._travel_be.repository.LaporanPengalamanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LaporanPengalamanService {

    private final LaporanPengalamanRepository laporanPengalamanRepository;

    @Autowired
    public LaporanPengalamanService(LaporanPengalamanRepository laporanPengalamanRepository) {
        this.laporanPengalamanRepository = laporanPengalamanRepository;
    }

    // getAll
    public List<LaporanPengalaman> getAllLaporan() {
        return laporanPengalamanRepository.findAll();
    }

    // getById
    public Optional<LaporanPengalaman> getLaporanById(Integer id) {
        return laporanPengalamanRepository.findById(id);
    }

    // create
    public LaporanPengalaman createLaporan(LaporanPengalaman laporan) {
        // Validasi simple
        if (laporan.getKomentar() == null || laporan.getKomentar().isEmpty()) {
            throw new IllegalArgumentException("Komentar tidak boleh kosong");
        }
        return laporanPengalamanRepository.save(laporan);
    }

    // update
    public LaporanPengalaman updateLaporan(Integer id, LaporanPengalaman details) {
        LaporanPengalaman existing = laporanPengalamanRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Laporan tidak ditemukan ID: " + id));

        existing.setKomentar(details.getKomentar());
        existing.setRating(details.getRating());
        // Note: typically we might also update the User, but usually reviews stay with
        // the original user.

        return laporanPengalamanRepository.save(existing);
    }

    // delete
    public void deleteLaporan(Integer id) {
        LaporanPengalaman existing = laporanPengalamanRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Laporan tidak ditemukan ID: " + id));
        laporanPengalamanRepository.delete(existing);
    }
}