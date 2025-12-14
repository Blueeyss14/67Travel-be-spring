package com.example._travel_be.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Data
public class Tiket {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idTicket;

    @ManyToOne
    @JoinColumn(name = "destinasi_id")
    private Destinasi destinasi;

    @ManyToOne
    @JoinColumn(name = "transportasi_id")
    private Transportasi transportasi;

    @ManyToOne
    @JoinColumn(name = "akomodasi_id")
    private Akomodasi akomodasi;

    private Integer jumPengunjung;
    private Double harga;
    private LocalDateTime waktu;

}
