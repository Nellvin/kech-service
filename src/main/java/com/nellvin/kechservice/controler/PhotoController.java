package com.nellvin.kechservice.controler;

import com.nellvin.kechservice.model.Photo;
import com.nellvin.kechservice.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.file.Paths;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
public class PhotoController {

    private String urlBase = "http://localhost:4200";

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

    @GetMapping("/api/photos/{id}/image")
    public ResponseEntity<Resource> getImage(@PathVariable(value = "id") Long photoId) throws FileNotFoundException {
        File file = new File(String.valueOf(Paths.get("photo-image/" + photoId + "/" + photoService.getPhoto(photoId).getFilePath())));
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=image.jpg");

        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(file.length())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }


}
