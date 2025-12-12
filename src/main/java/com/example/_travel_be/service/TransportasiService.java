package com.example._travel_be.service;

import com.example._travel_be.model.Transportasi;
import com.example._travel_be.repository.TransportasiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TransportasiService {
    
    @Autowired
    private TransportasiRepository transportasiRepository;
    
    // Get all transportasi
    public List<Transportasi> getAllTransportasi() {
        return transportasiRepository.findAll();
    }
    
    // Get transportasi by id
    public Optional<Transportasi> getTransportasiById(UUID id) {
        return transportasiRepository.findById(id);
    }
    
    // Create new transportasi
    public Transportasi createTransportasi(Transportasi transportasi) {
        return transportasiRepository.save(transportasi);
    }
    
    // Update transportasi
    public Transportasi updateTransportasi(UUID id, Transportasi transportasiDetails) {
        Transportasi transportasi = transportasiRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Transportasi tidak ditemukan dengan id: " + id));
        
        transportasi.setName(transportasiDetails.getName());
        transportasi.setJenisKendaraan(transportasiDetails.getJenisKendaraan());
        transportasi.setSeat(transportasiDetails.getSeat());
        transportasi.setHarga(transportasiDetails.getHarga());
        
        return transportasiRepository.save(transportasi);
    }
    
    // Delete transportasi
    public void deleteTransportasi(UUID id) {
        Transportasi transportasi = transportasiRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Transportasi tidak ditemukan dengan id: " + id));
        transportasiRepository.delete(transportasi);
    }
    
    // Get transportasi by jenis kendaraan
    public List<Transportasi> getTransportasiByJenisKendaraan(String jenisKendaraan) {
        return transportasiRepository.findByJenisKendaraan(jenisKendaraan);
    }
    
    // Search transportasi by name
    public List<Transportasi> searchTransportasiByName(String name) {
        return transportasiRepository.findByNameContainingIgnoreCase(name);
    }
}