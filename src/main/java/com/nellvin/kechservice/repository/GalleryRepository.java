package com.nellvin.kechservice.repository;

import com.nellvin.kechservice.model.Gallery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GalleryRepository extends JpaRepository<Gallery, Long> {
}
