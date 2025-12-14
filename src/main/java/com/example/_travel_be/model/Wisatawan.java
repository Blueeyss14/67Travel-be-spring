package com.example._travel_be.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.List;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class Wisatawan extends User implements CustomerServiceChat, PengingatJadwal {

    private String noHp;

    private String jadwal;

    @OneToMany(mappedBy = "wisatawan", cascade = CascadeType.ALL)
    private List<LaporanPengalaman> reviews;

    @Override
    public String balasChat() {
        return "Chat dibalas oleh Wisatawan";
    }

    @Override
    public void showNotification() {
        System.out.println("Menampilkan notifikasi jadwal untuk " + getNama());
    }
}