package com.example._travel_be.repository;

import com.example._travel_be.model.Akomodasi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AkomodasiRepository extends JpaRepository<Akomodasi, UUID> {
    // Kosong aja, JpaRepository udah nyediain fitur CRUD (Create, Read, Update, Delete) otomatis!
}