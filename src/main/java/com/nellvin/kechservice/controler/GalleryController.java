package com.nellvin.kechservice.controler;

import com.nellvin.kechservice.model.Gallery;
import com.nellvin.kechservice.model.Photo;
import com.nellvin.kechservice.model.Post;
import com.nellvin.kechservice.service.GalleryService;
import com.nellvin.kechservice.service.GalleryServiceImpl;
import com.nellvin.kechservice.service.PhotoService;
import com.nellvin.kechservice.utils.FileUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
public class GalleryController {

    private String urlBase = "http://localhost:8080";

    @Autowired
    GalleryService galleryService;

    @Autowired
    PhotoService photoService;

    public void setGalleryService(GalleryService galleryService) {
        this.galleryService = galleryService;
    }

    @CrossOrigin
    @GetMapping("/api/galleries")
    public List<Gallery> getAllGalleries() {
        return galleryService.retrieveGalleries();
    }

    @GetMapping("/api/galleries/{id}")
    public Gallery getGalleryDetails(@PathVariable(name = "id") Long galleryId) {
        return galleryService.getGallery(galleryId);
    }

    @GetMapping("/api/galleries/{id}/photos")
    public List<Photo> getGalleryPhotos(@PathVariable(name = "id") Long galleryId) {
        return galleryService.getGallery(galleryId).getPhotos();
    }

    @PostMapping("/api/galleries")
    public Gallery saveGallery(@RequestBody Gallery gallery) {
        Gallery gal = galleryService.saveGallery(gallery);
//        System.out.println(gallery.toString());
//        System.out.println(galleryService.getGallery(gallery.getId()).toString());
//        gallery.getPhotos().stream().map(Photo::getId).forEach(System.out::println);
        System.out.println("Gallery saved!");
        return gal;
    }

    @PostMapping("/api/galleries/{id}/photo")
    public void saveGalleryPhoto(@RequestBody Photo photo, @PathVariable(name = "id") Long galleryId) {
        System.out.println(photo);
        Gallery gal = galleryService.getGallery(galleryId);
        photo.setGallery(gal);
        gal.addPhoto(photo);
        galleryService.saveGallery(gal);
//        System.out.println(gallery.toString());
//        System.out.println(galleryService.getGallery(gallery.getId()).toString());
//        gallery.getPhotos().stream().map(Photo::getId).forEach(System.out::println);
        System.out.println("Gallery saved!");
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/api/galleries/{id}/co", method = RequestMethod.POST, consumes = {"multipart/form-data"})
    public Photo uploadPhotoToGallery(@RequestPart("photo") Photo photo, @PathVariable(name = "id") Long galleryId
            ,@RequestPart(value = "image", required = false) MultipartFile image
    ) throws IOException {
        System.out.println("debug co");
        if (image != null) {
//            System.out.println("REST multipart empty? :"+image.isEmpty());
            String fileName = StringUtils.cleanPath(image.getOriginalFilename());
            Photo savedPhoto = photoService.savePhoto(photo);
            savedPhoto.setCreateDate(new Date());
            savedPhoto.setUrl(urlBase+"/api/photos/"+savedPhoto.getId()+"/image");
            savedPhoto.setName(fileName);
            savedPhoto.setFilePath(fileName);
            Gallery gal = galleryService.getGallery(galleryId);
            savedPhoto.setGallery(gal);
            gal.addPhoto(photo);
            galleryService.saveGallery(gal);
            photoService.savePhoto(savedPhoto);
            String uploadPhotoDir = "photo-image/" + savedPhoto.getId();
            FileUploadUtil.saveFile(uploadPhotoDir, fileName, image);
            return savedPhoto;
        } else {
//            postService.savePost(post);
        }
        return null;
    }

    @PostMapping("/api/galleries/{id}/photos")
    public void saveGalleryPhotos(@RequestBody List<Photo> photos, @PathVariable(name = "id") Long galleryId) {
//        System.out.println(photo);
        Gallery gal = galleryService.getGallery(galleryId);
        for (int i = 0; i < photos.size(); i++) {
            Photo photo = photos.get(i);
            photo.setGallery(gal);
            gal.addPhoto(photo);
        }
        galleryService.saveGallery(gal);
//        System.out.println(gallery.toString());
//        System.out.println(galleryService.getGallery(gallery.getId()).toString());
//        gallery.getPhotos().stream().map(Photo::getId).forEach(System.out::println);
        System.out.println("Gallery saved!");
    }

    @DeleteMapping("/api/galleries/{id}")
    public void deleteGallery(@PathVariable(name = "id") Long galleryId) {
        galleryService.deleteGallery(galleryId);
        System.out.println("Gallery " + galleryId + " has been deleted");
    }

    @PutMapping("/api/galleries/{id}")
    public void updateGallery(@RequestBody Gallery gallery, @PathVariable(name = "id") Long galleryId) {
        Gallery gal = galleryService.getGallery(galleryId);
        if (gal != null)
            galleryService.updateGallery(gallery);
    }


}