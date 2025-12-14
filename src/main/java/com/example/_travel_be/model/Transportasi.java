// MODEL
package com.example._travel_be.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Entity
@Data
@Table(name = "transportasi")
public class Transportasi {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String nama;
    private String jenisKendaraan;
    private Integer maxPassenger;
    private Double harga;
    private String gambar;
}