package com.nellvin.kechservice.service;

import com.nellvin.kechservice.model.Gallery;
import com.nellvin.kechservice.model.Post;

import java.util.List;

public interface GalleryService  {

    public List<Gallery> retrieveGalleries();

    public Gallery getGallery(Long galleryId);

    public Gallery saveGallery(Gallery gallery);

    public void deleteGallery(Long galleryId);

    public void updateGallery(Gallery gallery);

}
