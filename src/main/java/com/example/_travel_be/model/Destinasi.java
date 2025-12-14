package com.example._travel_be.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Data
@NoArgsConstructor
public class Destinasi {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nama;
    private String lokasi;
    private String deskripsi;
    private Double rating;
    private Double harga;
    private Integer maxGuest;

    @OneToMany(mappedBy = "destinasi", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Tiket> tiket = new ArrayList<>();

    // Constructor based on class diagram
    public Destinasi(String nama, String lokasi, String deskripsi, Double rating, Double harga, Integer maxGuest) {
        this.nama = nama;
        this.lokasi = lokasi;
        this.deskripsi = deskripsi;
        this.rating = rating;
        this.harga = harga;
        this.maxGuest = maxGuest;
    }
}
