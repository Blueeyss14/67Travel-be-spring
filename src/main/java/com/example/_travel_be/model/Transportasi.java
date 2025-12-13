// MODEL
package com.example._travel_be.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.UUID;

@Entity
@Data
@Table(name = "transportasi")
public class Transportasi {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String nama;
    private String jenisKendaraan;
    private Integer maxPassenger;
    private Double harga;
    private String gambar;

    // Constructor sesuai diagram
    public Transportasi() {
    }

    public Transportasi(String nama, String jenisKendaraan, Double estimasiBiaya, 
                       Integer maxPassenger, String gambar) {
        this.nama = nama;
        this.jenisKendaraan = jenisKendaraan;
        this.harga = estimasiBiaya;
        this.maxPassenger = maxPassenger;
        this.gambar = gambar;
    }

    // Method tampilkanDetail() sesuai diagram
    public String tampilkanDetail() {
        return String.format(
            "Transportasi: %s\n" +
            "Jenis: %s\n" +
            "Max Passenger: %d orang\n" +
            "Harga: Rp %.2f\n" +
            "Gambar: %s",
            nama, jenisKendaraan, maxPassenger, harga, gambar
        );
    }

    // Getter dan Setter sudah di-handle oleh @Data dari Lombok
    // Tapi jika ingin eksplisit sesuai diagram:
    
    public void setMaxPassenger(Integer maxPassenger) {
        this.maxPassenger = maxPassenger;
    }

    public void setJenisKendaraan(String jenisKendaraan) {
        this.jenisKendaraan = jenisKendaraan;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNama() {
        return this.nama;
    }

    public String getJenisKendaraan() {
        return this.jenisKendaraan;
    }

    public Integer getMaxPassenger() {
        return this.maxPassenger;
    }

    public void setHarga(Double harga) {
        this.harga = harga;
    }

    public Double getHarga() {
        return this.harga;
    }
}