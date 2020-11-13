package com.nellvin.kechservice.service;

import com.nellvin.kechservice.model.Photo;

import java.util.List;

public interface  PhotoService {

    public List<Photo> retrievePhotos();

    public Photo getPhoto(Long photoId);

    public Photo savePhoto(Photo photo);

    public void deletePhoto(Long photoId);

    public void updatePhoto(Photo photo);

}