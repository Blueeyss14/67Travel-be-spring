package com.example._travel_be.features.destination.services;

import com.example._travel_be.features.destination.dto.DestinationRequest;
import com.example._travel_be.features.destination.model.Destination;
import com.example._travel_be.features.destination.repository.DestinationRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class DestinationService {

    private final DestinationRepository repo;

    public DestinationService(DestinationRepository repo) {
        this.repo = repo;
    }

    public Destination create(
            DestinationRequest req,
            String thumbnailUrl,
            List<String> imageUrls
    ) {
        Destination d = new Destination();
        d.setName(req.getName());
        d.setLocation(req.getLocation());
        d.setOwner(req.getOwner());
        d.setNumberOfGuest(req.getNumberOfGuest());
        d.setPrice(req.getPrice());
        d.setFacilities(req.getFacilities());
        d.setDescription(req.getDescription());
        d.setThumbnailUrl(thumbnailUrl);
        d.setImageUrls(imageUrls);
        d.setRating(0.0);

        return repo.save(d);
    }

    public Page<Destination> getAll(Pageable pageable) {
        return repo.findAll(pageable);
    }

    public Destination get(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("destination not found"));
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}
