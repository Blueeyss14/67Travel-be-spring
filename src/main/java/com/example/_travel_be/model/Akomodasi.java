package com.example._travel_be.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Entity
@Data
public class Akomodasi {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String gambar;
    private String nama;
    private String alamat;
    private String kategori;
    private Integer kapasitas;
    private Double rating;
    private Double price;

    @ElementCollection
    private List<String> fasilitas;

    @Column(length = 1000)
    private String deskripsi;

}
