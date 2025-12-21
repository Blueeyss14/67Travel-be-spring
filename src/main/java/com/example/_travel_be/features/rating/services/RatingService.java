package com.example._travel_be.features.rating.services;

import com.example._travel_be.features.auth.user.model.User;
import com.example._travel_be.features.auth.user.repository.UserRepository;
import com.example._travel_be.features.destination.model.Destination;
import com.example._travel_be.features.destination.repository.DestinationRepository;
import com.example._travel_be.features.rating.dto.RatingRequest;
import com.example._travel_be.features.rating.model.Rating;
import com.example._travel_be.features.rating.repository.RatingRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingService {

    private final RatingRepository ratingRepo;
    private final DestinationRepository destinationRepo;
    private final UserRepository userRepo;

    public RatingService(RatingRepository ratingRepo,
                         DestinationRepository destinationRepo,
                         UserRepository userRepo) {
        this.ratingRepo = ratingRepo;
        this.destinationRepo = destinationRepo;
        this.userRepo = userRepo;
    }

    public Rating create(Long destinationId, RatingRequest req, Long userId) {

        Destination destination = destinationRepo.findById(destinationId)
                .orElseThrow(() -> new RuntimeException("Destination not found"));

        User user = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Rating rating = new Rating();
        rating.setRate(req.getRate());
        rating.setDescription(req.getDescription());
        rating.setDestination(destination);
        rating.setUser(user);

        ratingRepo.save(rating);

        List<Rating> ratings = ratingRepo.findByDestinationId(destinationId);
        double avg = ratings.stream()
                .mapToInt(Rating::getRate)
                .average()
                .orElse(0);

        destination.setRating(avg);
        destinationRepo.save(destination);

        return rating;
    }
}
