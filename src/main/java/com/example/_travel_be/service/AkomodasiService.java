package com.example._travel_be.service;

import com.example._travel_be.model.Akomodasi;
import com.example._travel_be.repository.AkomodasiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AkomodasiService {

    @Autowired
    private AkomodasiRepository akomodasiRepository;

    // 1. Mengambil semua data
    public List<Akomodasi> getAllAkomodasi() {
        return akomodasiRepository.findAll();
    }

    // 2. Mengambil data berdasarkan ID
    public Optional<Akomodasi> getAkomodasiById(UUID id) {
        return akomodasiRepository.findById(id);
    }

    // 3. Menambah data baru
    public Akomodasi createAkomodasi(Akomodasi akomodasi) {
        return akomodasiRepository.save(akomodasi);
    }

    // 4. Update data (Cek dulu datanya ada gak)
    public Akomodasi updateAkomodasi(UUID id, Akomodasi akomodasiBaru) {
        Optional<Akomodasi> akomodasiLama = akomodasiRepository.findById(id);

        if (akomodasiLama.isPresent()) {
            Akomodasi existing = akomodasiLama.get();
            // Update satu per satu sesuai field di model kamu
            existing.setNama(akomodasiBaru.getNama());
            existing.setAlamat(akomodasiBaru.getAlamat());
            existing.setFasilitas(akomodasiBaru.getFasilitas());
            existing.setKategori(akomodasiBaru.getKategori());
            existing.setHarga(akomodasiBaru.getHarga());
            existing.setRating(akomodasiBaru.getRating());
            existing.setJarak(akomodasiBaru.getJarak());
            existing.setDeskripsi(akomodasiBaru.getDeskripsi());
            
            return akomodasiRepository.save(existing);
        } else {
            return null; // Kalau ID gak ketemu, balikin null
        }
    }

    // 5. Hapus data
    public boolean deleteAkomodasi(UUID id) {
        if (akomodasiRepository.existsById(id)) {
            akomodasiRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}