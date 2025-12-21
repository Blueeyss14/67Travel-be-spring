package com.example._travel_be.features.vehicle.services;

import com.cloudinary.Cloudinary;
import com.example._travel_be.features.vehicle.dto.VehicleRequest;
import com.example._travel_be.features.vehicle.model.Vehicle;
import com.example._travel_be.features.vehicle.repository.VehicleRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@Service
public class VehicleService {

    private final VehicleRepository repo;
    private final Cloudinary cloudinary;

    public VehicleService(VehicleRepository repo, Cloudinary cloudinary) {
        this.repo = repo;
        this.cloudinary = cloudinary;
    }

    public Vehicle create(VehicleRequest req, MultipartFile thumbnail) throws Exception {
        String thumbnailUrl = uploadToCloudinary(thumbnail);

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

    public Vehicle update(Long id, VehicleRequest req, MultipartFile thumbnail) throws Exception {
        Vehicle v = get(id);

        v.setName(req.getName());
        v.setPrice(req.getPrice());
        v.setMaxPassenger(req.getMaxPassenger());

        if (thumbnail != null) {
            String thumbnailUrl = uploadToCloudinary(thumbnail);
            v.setThumbnailUrl(thumbnailUrl);
        }

        return repo.save(v);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }

    private String uploadToCloudinary(MultipartFile file) throws Exception {
        Map<?, ?> result = cloudinary.uploader().upload(
                file.getBytes(),
                Map.of("folder", "vehicles")
        );
        return result.get("secure_url").toString();
    }
}
