package com.example._travel_be.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.UUID;

@Entity
@Data
public class Akomodasi {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String nama;
    private String alamat;
    private String fasilitas;
    private String kategori;
    private Double harga;
    private Double rating;
    private Double jarak;
    private String deskripsi;
}
