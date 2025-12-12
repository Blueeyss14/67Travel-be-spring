package com.example._travel_be.repository;

import com.example._travel_be.model.Transportasi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TransportasiRepository extends JpaRepository<Transportasi, UUID> {
    List<Transportasi> findByJenisKendaraan(String jenisKendaraan);
    List<Transportasi> findByNameContainingIgnoreCase(String name);
}