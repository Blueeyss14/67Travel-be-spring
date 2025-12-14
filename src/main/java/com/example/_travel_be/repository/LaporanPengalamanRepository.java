package com.example._travel_be.repository;

import com.example._travel_be.model.LaporanPengalaman;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

// JpaRepository<Entity, Tipe ID>
public interface LaporanPengalamanRepository extends JpaRepository<LaporanPengalaman, Integer> {
    // Custom query methods if needed
    List<LaporanPengalaman> findByUserId(Integer userId);
}