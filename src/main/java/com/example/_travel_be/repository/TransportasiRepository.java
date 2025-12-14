// REPOSITORY
package com.example._travel_be.repository;

import com.example._travel_be.model.Transportasi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransportasiRepository extends JpaRepository<Transportasi, Integer> {
    List<Transportasi> findByJenisKendaraan(String jenisKendaraan);

    List<Transportasi> findByNamaContainingIgnoreCase(String nama); // Ganti name jadi nama
}