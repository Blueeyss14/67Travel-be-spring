package com.example._travel_be.repository;

import com.example._travel_be.model.LaporanPengalaman;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface LaporanPengalamanRepository extends JpaRepository<LaporanPengalaman, UUID> {
    List<LaporanPengalaman> findByWisatawanId(UUID wisatawanId);
}