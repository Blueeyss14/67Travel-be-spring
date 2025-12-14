package com.example._travel_be.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class LaporanPengalaman {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String komentar;
    private Double rating;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Wisatawan user;

    // Constructor based on class diagram
    public LaporanPengalaman(String komentar, Double rating, Wisatawan user) {
        this.komentar = komentar;
        this.rating = rating;
        this.user = user;
    }

    // Method based on class diagram
    public void review() {
        System.out.println("Review by " + user.getNama() + ": " + komentar + " (" + rating + ")");
    }
}
