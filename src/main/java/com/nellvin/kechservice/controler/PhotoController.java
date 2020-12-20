package com.nellvin.kechservice.controler;

import com.nellvin.kechservice.model.Photo;
import com.nellvin.kechservice.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
public class PhotoController {

    @Autowired
    PhotoService photoService;

    public void setPhotoService(PhotoService photoService) {
        this.photoService = photoService;
    }

    @CrossOrigin
    @GetMapping("/api/photos")
    public List<Photo> getAllPhotos() {
        return photoService.retrievePhotos();
    }

    @PostMapping("/api/photos")
    public void saveSermon(@RequestBody Photo photo) {
        photoService.savePhoto(photo);
        System.out.println("Photo saved!");
    }

    @DeleteMapping("/api/photos/{id}")
    public void deleteSermon(@PathVariable(name = "id") Long photoId) {
        photoService.deletePhoto(photoId);
        System.out.println("Photo " + photoId + " has been deleted");
    }

    @PutMapping("/api/photos/{id}")
    public void updateSermon(@RequestBody Photo photo, @PathVariable(name = "id") Long photoId) {
        Photo pho= photoService.getPhoto(photoId);
        if (pho != null)
            photoService.updatePhoto(photo);

    }



}
