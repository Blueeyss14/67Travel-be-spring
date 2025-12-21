package com.example._travel_be.features.auth.user.model;

import com.example._travel_be.features.destination.model.Destination;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nama;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(unique = true, nullable = false)
    private String noTelpon;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String role = "USER";

    @ManyToMany(mappedBy = "bookmarkedBy")
    private Set<Destination> bookmarks = new HashSet<>();
}
