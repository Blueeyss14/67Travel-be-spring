package com.example._travel_be.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.UUID;
import java.time.LocalDateTime;

@Entity
@Data
public class Tiket {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idTicket;

    @ManyToOne
    @JoinColumn(name = "destinasi_id")
    private Destinasi destinasi;

    @ManyToOne
    @JoinColumn(name = "transportasi_id")
    private Transportasi transportasi;

    @ManyToOne
    @JoinColumn(name = "akomodasi_id")
    private Akomodasi akomodasi;

    private Integer jmlPengunjung;
    private Double hargaTotal;
    private LocalDateTime waktu;

    public void hitungBiaya() {
        double hargaDestinasi = (destinasi != null) ? destinasi.getHarga() : 0;
        double hargaTransport = (transportasi != null) ? transportasi.getHarga() : 0;
        double hargaAkomodasi = (akomodasi != null) ? akomodasi.getHarga() : 0;

        this.hargaTotal = (hargaDestinasi + hargaTransport + hargaAkomodasi) * jmlPengunjung;
    }
}
