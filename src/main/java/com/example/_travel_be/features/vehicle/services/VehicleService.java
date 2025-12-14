package com.example._travel_be.features.vehicle.services;

import com.example._travel_be.features.vehicle.dto.VehicleRequest;
import com.example._travel_be.features.vehicle.model.Vehicle;
import com.example._travel_be.features.vehicle.repository.VehicleRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class VehicleService {

    private final VehicleRepository repo;

    public VehicleService(VehicleRepository repo) {
        this.repo = repo;
    }

    public Vehicle create(VehicleRequest req, String thumbnailUrl) {
        Vehicle v = new Vehicle();
        v.setName(req.getName());
        v.setPrice(req.getPrice());
        v.setMaxPassenger(req.getMaxPassenger());
        v.setThumbnailUrl(thumbnailUrl);
        return repo.save(v);
    }

    public Page<Vehicle> getAll(Pageable pageable) {
        return repo.findAll(pageable);
    }

    public Vehicle get(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("vehicle not found"));
    }

    public Vehicle update(Long id, VehicleRequest req, String thumbnailUrl) {
        Vehicle v = get(id);
        v.setName(req.getName());
        v.setPrice(req.getPrice());
        v.setMaxPassenger(req.getMaxPassenger());

        if (thumbnailUrl != null) {
            v.setThumbnailUrl(thumbnailUrl);
        }

        return repo.save(v);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}
