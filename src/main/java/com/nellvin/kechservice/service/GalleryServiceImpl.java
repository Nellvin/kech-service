package com.nellvin.kechservice.service;

import com.nellvin.kechservice.model.Gallery;
import com.nellvin.kechservice.repository.GalleryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GalleryServiceImpl implements GalleryService{

    @Autowired
    private GalleryRepository galleryRepository;

    @Override
    public List<Gallery> retrieveGalleries() {
        return galleryRepository.findAll();
    }

    @Override
    public Gallery getGallery(Long galleryId) {
        return galleryRepository.findById(galleryId).get();
    }

    @Override
    public Gallery saveGallery(Gallery gallery) {
        galleryRepository.save(gallery);
        return gallery;
    }

    @Override
    public void deleteGallery(Long galleryId) {
        galleryRepository.deleteById(galleryId);
    }

    @Override
    public void updateGallery(Gallery gallery) {
        galleryRepository.save(gallery);
    }
}
