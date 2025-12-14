package com.example._travel_be.repository;

import com.example._travel_be.model.Akomodasi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AkomodasiRepository extends JpaRepository<Akomodasi, Integer> {
}