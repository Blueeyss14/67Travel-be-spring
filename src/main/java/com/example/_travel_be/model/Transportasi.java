package com.example._travel_be.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.UUID;

@Entity
@Data
public class Transportasi {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String name;
    private String jenisKendaraan;
    private Integer seat;
    private Double harga;
}
