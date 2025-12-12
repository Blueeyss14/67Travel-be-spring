package com.example._travel_be.service;

import com.example._travel_be.model.LaporanPengalaman;
import com.example._travel_be.repository.LaporanPengalamanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class LaporanPengalamanService {

    @Autowired
    private LaporanPengalamanRepository laporanPengalamanRepository;

    public List<LaporanPengalaman> getAllLaporan() {
        return laporanPengalamanRepository.findAll();
    }

    public LaporanPengalaman getLaporanById(UUID id) {
        return laporanPengalamanRepository.findById(id).orElse(null);
    }

    public List<LaporanPengalaman> getLaporanByWisatawanId(UUID wisatawanId) {
        return laporanPengalamanRepository.findByWisatawanId(wisatawanId);
    }

    public LaporanPengalaman createLaporan(LaporanPengalaman laporanPengalaman) {
        return laporanPengalamanRepository.save(laporanPengalaman);
    }

    public LaporanPengalaman updateLaporan(UUID id, LaporanPengalaman laporanPengalaman) {
        if (laporanPengalamanRepository.existsById(id)) {
            laporanPengalaman.setId(id);
            return laporanPengalamanRepository.save(laporanPengalaman);
        }
        return null;
    }

    public boolean deleteLaporan(UUID id) {
        if (laporanPengalamanRepository.existsById(id)) {
            laporanPengalamanRepository.deleteById(id);
            return true;
        }
        return false;
    }
}