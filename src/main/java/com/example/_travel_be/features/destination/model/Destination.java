package com.example._travel_be.features.destination.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "destinations")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Destination {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String location;
    private String owner;
    private Integer numberOfGuest;
    private Double rating;
    private Double price;

    private String thumbnailUrl;

    @ElementCollection
    private List<String> facilities;

    @ElementCollection
    private List<String> imageUrls;

    @Column(columnDefinition = "TEXT")
    private String description;
}
