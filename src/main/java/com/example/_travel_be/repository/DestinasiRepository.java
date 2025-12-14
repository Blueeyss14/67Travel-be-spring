package com.example._travel_be.repository;

import com.example._travel_be.model.Destinasi;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

// JpaRepository<Entity, Tipe ID> menyediakan CRUD (Save, FindAll, FindById, DeleteById) secara otomatis
public interface DestinasiRepository extends JpaRepository<Destinasi, Integer> {

    // Metode Kustom yang dibuat berdasarkan kebutuhan Class Diagram (misalnya
    // filter berdasarkan lokasi)
    List<Destinasi> findByLokasi(String lokasi);

    // Metode Kustom untuk mencari Destinasi dengan rating di atas nilai tertentu
    List<Destinasi> findByRatingGreaterThanEqual(Double rating);
}