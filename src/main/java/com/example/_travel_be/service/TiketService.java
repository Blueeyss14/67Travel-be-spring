package com.example._travel_be.service;

import com.example._travel_be.model.Tiket;
import com.example._travel_be.repository.TiketRepository;
import com.example._travel_be.repository.DestinasiRepository;
import com.example._travel_be.repository.TransportasiRepository;
import com.example._travel_be.repository.AkomodasiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TiketService {

    @Autowired
    private TiketRepository tiketRepository;

    @Autowired
    private DestinasiRepository destinasiRepository;

    @Autowired
    private TransportasiRepository transportasiRepository;

    @Autowired
    private AkomodasiRepository akomodasiRepository;

    public List<Tiket> getAllTiket() {
        return tiketRepository.findAll();
    }

    public Optional<Tiket> getTiketById(Integer id) {
        return tiketRepository.findById(id);
    }

    public Tiket createTiket(Tiket tiket) {
        if (tiket.getWaktu() == null) {
            tiket.setWaktu(LocalDateTime.now());
        }

        // Ensure entities are fetched from DB to get correct prices
        if (tiket.getDestinasi() != null && tiket.getDestinasi().getId() != null) {
            tiket.setDestinasi(destinasiRepository.findById(tiket.getDestinasi().getId()).orElse(null));
        }
        if (tiket.getTransportasi() != null && tiket.getTransportasi().getId() != null) {
            tiket.setTransportasi(transportasiRepository.findById(tiket.getTransportasi().getId()).orElse(null));
        }
        if (tiket.getAkomodasi() != null && tiket.getAkomodasi().getId() != null) {
            tiket.setAkomodasi(akomodasiRepository.findById(tiket.getAkomodasi().getId()).orElse(null));
        }

        tiket.setHarga(hitungBiaya(tiket));
        return tiketRepository.save(tiket);
    }

    public Double hitungBiaya(Tiket tiket) {
        double hargaDestinasi = (tiket.getDestinasi() != null && tiket.getDestinasi().getHarga() != null)
                ? tiket.getDestinasi().getHarga()
                : 0;
        double hargaTransport = (tiket.getTransportasi() != null && tiket.getTransportasi().getHarga() != null)
                ? tiket.getTransportasi().getHarga()
                : 0;
        double hargaAkomodasi = (tiket.getAkomodasi() != null && tiket.getAkomodasi().getPrice() != null)
                ? tiket.getAkomodasi().getPrice()
                : 0;

        return (hargaDestinasi + hargaTransport + hargaAkomodasi) * tiket.getJumPengunjung();
    }

    public void deleteTiket(Integer id) {
        tiketRepository.deleteById(id);
    }
}
