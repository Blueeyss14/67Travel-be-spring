package com.example._travel_be.service;

import com.example._travel_be.model.Tiket;
import com.example._travel_be.repository.TiketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TiketService {

    @Autowired
    private TiketRepository tiketRepository;

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
        tiket.setHarga(hitungBiaya(tiket)); // setHargaTotal -> setHarga
        return tiketRepository.save(tiket);
    }

    public Double hitungBiaya(Tiket tiket) {
        double hargaDestinasi = (tiket.getDestinasi() != null) ? tiket.getDestinasi().getHarga() : 0;
        double hargaTransport = (tiket.getTransportasi() != null) ? tiket.getTransportasi().getHarga() : 0;
        double hargaAkomodasi = (tiket.getAkomodasi() != null) ? tiket.getAkomodasi().getPrice() : 0;

        return (hargaDestinasi + hargaTransport + hargaAkomodasi) * tiket.getJumPengunjung(); // getJmlPengunjung ->
                                                                                              // getJumPengunjung
    }

    public void deleteTiket(Integer id) {
        tiketRepository.deleteById(id);
    }
}
