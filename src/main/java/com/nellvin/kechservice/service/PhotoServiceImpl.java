package com.nellvin.kechservice.service;

import com.nellvin.kechservice.model.Photo;
import com.nellvin.kechservice.repository.PhotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhotoServiceImpl implements PhotoService {

    @Autowired
    PhotoRepository photoRepository;

    @Override
    public List<Photo> retrievePhotos() {
        return photoRepository.findAll();
    }

    @Override
    public Photo getPhoto(Long photoId) {
        return photoRepository.findById(photoId).get();
    }

    @Override
    public Photo savePhoto(Photo photo) {
        return photoRepository.save(photo);
    }

    @Override
    public void deletePhoto(Long photoId) {
        photoRepository.deleteById(photoId);
    }

    @Override
    public void updatePhoto(Photo photo) {
        photoRepository.save(photo);
    }
}
