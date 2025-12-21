package com.example._travel_be.features.accommodation.services;

import com.cloudinary.Cloudinary;
import com.example._travel_be.features.accommodation.model.Accommodation;
import com.example._travel_be.features.accommodation.repository.AccommodationRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@Service
public class AccommodationService {

    private final AccommodationRepository repo;
    private final Cloudinary cloudinary;

    public AccommodationService(AccommodationRepository repo, Cloudinary cloudinary) {
        this.repo = repo;
        this.cloudinary = cloudinary;
    }

    public Accommodation create(
            String name,
            Double latitude,
            Double longitude,
            Double price,
            MultipartFile thumbnail
    ) {
        Accommodation a = new Accommodation();
        a.setName(name);
        a.setLatitude(latitude);
        a.setLongitude(longitude);
        a.setPrice(price);

        // ✅ UPLOAD THUMBNAIL
        if (thumbnail != null && !thumbnail.isEmpty()) {
            try {
                Map upload = cloudinary.uploader().upload(
                        thumbnail.getBytes(),
                        Map.of("folder", "accommodations")
                );
                a.setThumbnailUrl(upload.get("secure_url").toString());
            } catch (Exception e) {
                throw new RuntimeException("Upload thumbnail gagal");
            }
        }

        return repo.save(a);
    }

    public Page<Accommodation> getAll(Pageable pageable) {
        return repo.findAll(pageable);
    }

    public Accommodation get(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("accommodation not found"));
    }

    public Accommodation update(
            Long id,
            String name,
            Double latitude,
            Double longitude,
            Double price,
            MultipartFile thumbnail
    ) {
        Accommodation a = get(id);
        a.setName(name);
        a.setLatitude(latitude);
        a.setLongitude(longitude);
        a.setPrice(price);

        if (thumbnail != null && !thumbnail.isEmpty()) {
            try {
                Map upload = cloudinary.uploader().upload(
                        thumbnail.getBytes(),
                        Map.of("folder", "accommodations")
                );
                a.setThumbnailUrl(upload.get("secure_url").toString());
            } catch (Exception e) {
                throw new RuntimeException("Upload thumbnail gagal");
            }
        }

        return repo.save(a);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}
