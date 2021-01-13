package com.nellvin.kechservice.service;

import com.nellvin.kechservice.model.Event;
import com.nellvin.kechservice.model.Gallery;
import com.nellvin.kechservice.model.Photo;
import com.nellvin.kechservice.repository.GalleryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class GalleryServiceImpl implements GalleryService{

    @Autowired
    private GalleryRepository galleryRepository;

    @Override
    public List<Gallery> retrieveGalleries() {
        List<Gallery> list = galleryRepository.findAll();
        Collections.sort(list, Comparator.comparing(Gallery::getCreateDate));
        return list;
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

    public void addPhotoToGallery(Long galleryId, Photo photo){
        Gallery gal = galleryRepository.findById(galleryId).get();
        gal.addPhoto(photo);
        galleryRepository.save(gal);
    }
}
