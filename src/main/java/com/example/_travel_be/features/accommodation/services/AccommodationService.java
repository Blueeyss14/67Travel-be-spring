package com.example._travel_be.features.accommodation.services;

import com.example._travel_be.features.accommodation.dto.AccommodationRequest;
import com.example._travel_be.features.accommodation.model.Accommodation;
import com.example._travel_be.features.accommodation.repository.AccommodationRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AccommodationService {

    private final AccommodationRepository repo;

    public AccommodationService(AccommodationRepository repo) {
        this.repo = repo;
    }

    public Accommodation create(AccommodationRequest req) {
        Accommodation a = new Accommodation();
        a.setName(req.getName());
        a.setLatitude(req.getLatitude());
        a.setLongitude(req.getLongitude());
        a.setPrice(req.getPrice());
        return repo.save(a);
    }

    public Page<Accommodation> getAll(Pageable pageable) {
        return repo.findAll(pageable);
    }

    public Accommodation get(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("accommodation not found"));
    }

    public Accommodation update(Long id, AccommodationRequest req) {
        Accommodation a = get(id);
        a.setName(req.getName());
        a.setLatitude(req.getLatitude());
        a.setLongitude(req.getLongitude());
        a.setPrice(req.getPrice());
        return repo.save(a);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}
