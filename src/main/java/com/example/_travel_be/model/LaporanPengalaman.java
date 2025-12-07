package com.example._travel_be.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.UUID;

@Entity
@Data
public class LaporanPengalaman {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String komentar;
    private Double rating;

    @ManyToOne
    @JoinColumn(name = "wisatawan_id")
    private Wisatawan wisatawan;
}
