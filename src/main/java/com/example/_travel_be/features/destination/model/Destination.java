package com.example._travel_be.features.destination.model;

import com.example._travel_be.features.auth.user.model.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "destinations")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Destination {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long adminId;

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

    @ManyToMany
    @JoinTable(
            name = "destination_bookmarks",
            joinColumns = @JoinColumn(name = "destination_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private Set<User> bookmarkedBy = new HashSet<>();
}
