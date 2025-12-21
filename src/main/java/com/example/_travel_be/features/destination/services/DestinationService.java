package com.example._travel_be.features.destination.services;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example._travel_be.features.auth.admin.model.Admin;
import com.example._travel_be.features.auth.admin.repository.AdminRepository;
import com.example._travel_be.features.auth.user.model.User;
import com.example._travel_be.features.auth.user.repository.UserRepository;
import com.example._travel_be.features.destination.dto.DestinationRequest;
import com.example._travel_be.features.destination.model.Destination;
import com.example._travel_be.features.destination.repository.DestinationRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

@Service
public class DestinationService {

    private final DestinationRepository repo;
    private final Cloudinary cloudinary;
    private final AdminRepository adminRepository;
    private final UserRepository userRepository;

    public DestinationService(DestinationRepository repo,
                              Cloudinary cloudinary,
                              AdminRepository adminRepository,
                              UserRepository userRepository) {
        this.repo = repo;
        this.cloudinary = cloudinary;
        this.adminRepository = adminRepository;
        this.userRepository = userRepository;
    }

    public Destination create(DestinationRequest req,
                              MultipartFile thumbnail,
                              List<MultipartFile> images,
                              Long adminId) throws Exception {
        Admin admin = adminRepository.findById(adminId)
                .orElseThrow(() -> new RuntimeException("Admin tidak ditemukan"));

        String thumbnailUrl = upload(thumbnail, "destinations/thumbnails");
        List<String> imageUrls = new ArrayList<>();
        if (images != null) {
            for (MultipartFile img : images) {
                imageUrls.add(upload(img, "destinations/images"));
            }
        }

        Destination d = new Destination();
        d.setAdminId(admin.getId());
        d.setName(req.getName());
        d.setLocation(req.getLocation());
        d.setOwner(req.getOwner());
        d.setNumberOfGuest(req.getMaxOfGuest());
        d.setPrice(req.getPrice());
        d.setFacilities(req.getFacilities());
        d.setDescription(req.getDescription());
        d.setThumbnailUrl(thumbnailUrl);
        d.setImageUrls(imageUrls);
        d.setRating(0.0);

        return repo.save(d);
    }

    public Page<Destination> getAll(Pageable pageable, Long userId) {
        Page<Destination> page = repo.findAll(pageable);
        if (userId == null) return page;
        User user = userRepository.findById(userId).orElse(null);
        if (user == null) return page;
        page.forEach(d -> d.setBookmarkedBy(new HashSet<>(d.getBookmarkedBy())));
        return page;
    }

    public Destination get(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("destination not found"));
    }

    public Destination update(Long id,
                              DestinationRequest req,
                              MultipartFile thumbnail,
                              List<MultipartFile> images,
                              Long adminId) throws Exception {
        Destination d = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("destination not found"));

        if (!Objects.equals(d.getAdminId(), adminId)) {
            throw new RuntimeException("Tidak punya akses");
        }

        d.setName(req.getName());
        d.setLocation(req.getLocation());
        d.setOwner(req.getOwner());
        d.setNumberOfGuest(req.getMaxOfGuest());
        d.setPrice(req.getPrice());
        d.setFacilities(req.getFacilities());
        d.setDescription(req.getDescription());

        if (thumbnail != null) {
            d.setThumbnailUrl(upload(thumbnail, "destinations/thumbnails"));
        }

        if (images != null && !images.isEmpty()) {
            List<String> imageUrls = new ArrayList<>();
            for (MultipartFile img : images) {
                imageUrls.add(upload(img, "destinations/images"));
            }
            d.setImageUrls(imageUrls);
        }

        return repo.save(d);
    }

    public void delete(Long id, Long adminId) {
        Destination d = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("destination not found"));
        if (!Objects.equals(d.getAdminId(), adminId)) {
            throw new RuntimeException("Tidak punya akses");
        }
        repo.delete(d);
    }

    public Destination toggleBookmark(Long destinationId, Long userId) {
        Destination d = repo.findById(destinationId)
                .orElseThrow(() -> new RuntimeException("destination not found"));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User tidak ditemukan"));

        if (d.getBookmarkedBy().contains(user)) {
            d.getBookmarkedBy().remove(user);
        } else {
            d.getBookmarkedBy().add(user);
        }

        return repo.save(d);
    }

    private String upload(MultipartFile file, String folder) throws Exception {
        Map<?, ?> result = cloudinary.uploader().upload(
                file.getBytes(),
                ObjectUtils.asMap("folder", folder)
        );
        return result.get("secure_url").toString();
    }
}
