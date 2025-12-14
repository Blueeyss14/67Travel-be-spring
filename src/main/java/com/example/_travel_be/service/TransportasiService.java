package com.example._travel_be.service;

import com.example._travel_be.model.Transportasi;
import com.example._travel_be.repository.TransportasiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransportasiService {

    @Autowired
    private TransportasiRepository transportasiRepository;

    // Get all transportasi
    public List<Transportasi> getAllTransportasi() {
        return transportasiRepository.findAll();
    }

    // Get transportasi by id
    public Optional<Transportasi> getTransportasiById(Integer id) {
        return transportasiRepository.findById(id);
    }

    // Create new transportasi
    public Transportasi createTransportasi(Transportasi transportasi) {
        return transportasiRepository.save(transportasi);
    }

    // Update transportasi
    public Transportasi updateTransportasi(Integer id, Transportasi transportasiDetails) {
        Transportasi transportasi = transportasiRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Transportasi tidak ditemukan dengan id: " + id));

        transportasi.setNama(transportasiDetails.getNama());
        transportasi.setJenisKendaraan(transportasiDetails.getJenisKendaraan());
        transportasi.setMaxPassenger(transportasiDetails.getMaxPassenger());
        transportasi.setHarga(transportasiDetails.getHarga());
        transportasi.setGambar(transportasiDetails.getGambar());

        return transportasiRepository.save(transportasi);
    }

    // Delete transportasi
    public void deleteTransportasi(Integer id) {
        Transportasi transportasi = transportasiRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Transportasi tidak ditemukan dengan id: " + id));
        transportasiRepository.delete(transportasi);
    }

    // Get transportasi by jenis kendaraan
    public List<Transportasi> getTransportasiByJenisKendaraan(String jenisKendaraan) {
        return transportasiRepository.findByJenisKendaraan(jenisKendaraan);
    }

    // Search transportasi by nama
    public List<Transportasi> searchTransportasiByNama(String nama) {
        return transportasiRepository.findByNamaContainingIgnoreCase(nama);
    }

    public void tampilkanDetail(Integer id) {
        Optional<Transportasi> tOpt = transportasiRepository.findById(id);
        if (tOpt.isPresent()) {
            Transportasi t = tOpt.get();
            System.out.println("Transportasi: " + t.getNama());
            System.out.println("Jenis: " + t.getJenisKendaraan());
            System.out.println("Max Passenger: " + t.getMaxPassenger());
            System.out.println("Harga: " + t.getHarga());
            System.out.println("Gambar: " + t.getGambar());
        } else {
            System.out.println("Transportasi tidak ditemukan ID: " + id);
        }
    }
}