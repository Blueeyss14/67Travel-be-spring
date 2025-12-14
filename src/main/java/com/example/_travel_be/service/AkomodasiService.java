package com.example._travel_be.service;

import com.example._travel_be.model.Akomodasi;
import com.example._travel_be.repository.AkomodasiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AkomodasiService {

    @Autowired
    private AkomodasiRepository akomodasiRepository;

    // 1. Mengambil semua data
    public List<Akomodasi> getAllAkomodasi() {
        return akomodasiRepository.findAll();
    }

    // 2. Mengambil data berdasarkan ID
    public Optional<Akomodasi> getAkomodasiById(Integer id) {
        return akomodasiRepository.findById(id);
    }

    // 3. Menambah data baru
    public Akomodasi createAkomodasi(Akomodasi akomodasi) {
        return akomodasiRepository.save(akomodasi);
    }

    // 4. Update data (Cek dulu datanya ada gak)
    public Akomodasi updateAkomodasi(Integer id, Akomodasi akomodasiBaru) {
        Optional<Akomodasi> akomodasiLama = akomodasiRepository.findById(id);

        if (akomodasiLama.isPresent()) {
            Akomodasi existing = akomodasiLama.get();
            // Update satu per satu sesuai field di model kamu
            existing.setNama(akomodasiBaru.getNama());
            existing.setAlamat(akomodasiBaru.getAlamat());
            existing.setFasilitas(akomodasiBaru.getFasilitas());
            existing.setKategori(akomodasiBaru.getKategori());
            existing.setPrice(akomodasiBaru.getPrice());
            existing.setRating(akomodasiBaru.getRating());
            existing.setKapasitas(akomodasiBaru.getKapasitas());
            existing.setGambar(akomodasiBaru.getGambar());
            existing.setDeskripsi(akomodasiBaru.getDeskripsi());

            return akomodasiRepository.save(existing);
        } else {
            return null; // Kalau ID gak ketemu, balikin null
        }
    }

    // 5. Hapus data
    public boolean deleteAkomodasi(Integer id) {
        if (akomodasiRepository.existsById(id)) {
            akomodasiRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    public void tampilkanDetail(Integer id) {
        Optional<Akomodasi> akomodasiOpt = akomodasiRepository.findById(id);
        if (akomodasiOpt.isPresent()) {
            Akomodasi a = akomodasiOpt.get();
            System.out.println("Akomodasi: " + a.getNama());
            System.out.println("Alamat: " + a.getAlamat());
            System.out.println("Kategori: " + a.getKategori());
            System.out.println("Kapasitas: " + a.getKapasitas());
            System.out.println("Rating: " + a.getRating());
            System.out.println("Price: " + a.getPrice());
            System.out.println("Fasilitas: " + a.getFasilitas());
            System.out.println("Deskripsi: " + a.getDeskripsi());
        } else {
            System.out.println("Akomodasi dengan ID " + id + " tidak ditemukan.");
        }
    }
}