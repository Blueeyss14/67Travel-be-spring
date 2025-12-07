package com.example._travel_be.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.UUID;

@Entity
@Data
public class Destinasi {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String nama;
    private String lokasi;
    private String deskripsi;
    private Double rating;
    private Double harga;
    private String image;
}
