package com.nellvin.kechservice.controler;

import com.nellvin.kechservice.model.Sermon;
import com.nellvin.kechservice.service.SermonService;
import com.nellvin.kechservice.utils.FileUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
public class SermonController {

    @Autowired
    private SermonService sermonService;

    public void setSermonService(SermonService sermonService) {
        this.sermonService = sermonService;
    }

    @CrossOrigin
    @GetMapping("/api/sermons")
    public List<Sermon> getAllSermons() {
        return sermonService.retrieveSermons();
    }

    @GetMapping("/api/sermons/{id}")
    public Sermon getSermonById(@PathVariable(value = "id") Long sermonId) {
        return sermonService.getSermon(sermonId);
    }

    @GetMapping("/api/sermon/{id}/image")
    public ResponseEntity<Resource> getImage(@PathVariable(value = "id") Long sermonId) throws FileNotFoundException {
        File file = new File(String.valueOf(Paths.get("sermon-photo/" + sermonId + "/" + sermonService.getSermon(sermonId).getPhotoUrl())));
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=image.jpg");

        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(file.length())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }

//    @GetMapping("/api/sermon/image")
//    public ResponseEntity<Resource> getImage2() throws FileNotFoundException {
//        File file = new File(String.valueOf(Paths.get("sermon-photo/1/portretProboscis_monkey_(Nasalis_larvatus)_male_head_0.jpg")));
//        System.out.println(Paths.get("sermon-photo/1/portretProboscis_monkey_(Nasalis_larvatus)_male_head_0.jpg"));
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=image.jpg");
//
//        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
//        return ResponseEntity.ok()
//                .headers(headers)
//                .contentLength(file.length())
//                .contentType(MediaType.APPLICATION_OCTET_STREAM)
//                .body(resource);
//    }

    @GetMapping("/api/sermon/{id}/audio")
    public ResponseEntity<Resource> getSermonAudio(@PathVariable(value = "id") Long sermonId) throws FileNotFoundException {
        File file = new File(String.valueOf(Paths.get("sermon-audio/" + sermonId + "/" + sermonService.getSermon(sermonId).getAudioUrl())));
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=audio.mp3");

        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(file.length())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }

    @PostMapping("/api/sermons")
    public void saveSermon(@RequestBody Sermon sermon) {
        sermonService.saveSermon(sermon);
        System.out.println("Sermon saved!");
    }

    @PostMapping("/api/sermons2")
    public void saveSermon2(Sermon sermon, @RequestParam("image") MultipartFile multipartFile1,
                            @RequestParam("music") MultipartFile multipartFile2) throws IOException {

        String photoName = StringUtils.cleanPath(multipartFile1.getOriginalFilename());
        sermon.setPhotoUrl(photoName);

        String audioName = StringUtils.cleanPath(multipartFile2.getOriginalFilename());
        sermon.setAudioUrl(audioName);

        Sermon savedSermon = sermonService.saveSermon(sermon);
        sermon.setUrl("http://localhost:8080/api/sermon/" + savedSermon.getId() + "/audio");
        sermonService.updateSermon(sermon);


        String uploadPhotoDir = "sermon-photo/" + savedSermon.getId();
        String uploadAudioDir = "sermon-audio/" + savedSermon.getId();

        FileUploadUtil.saveFile(uploadPhotoDir, photoName, multipartFile1);
        FileUploadUtil.saveFile(uploadAudioDir, audioName, multipartFile2);

        BufferedImage originalImage = ImageIO.read(new File(String.valueOf(Paths.get("sermon-photo/" + savedSermon.getId() + "/" + photoName))));
        int targetWidth = 50;
        int targetHeight = originalImage.getHeight() / (originalImage.getWidth() / targetWidth);

        BufferedImage resizedImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics2D = resizedImage.createGraphics();
        graphics2D.drawImage(originalImage, 0, 0, targetWidth, targetHeight, null);
        graphics2D.dispose();

        FileUploadUtil.saveFile(uploadPhotoDir, photoName.replace(".jpg", "_small.jpg"), resizedImage);

    }

    @RequestMapping(value = "/api/sermons/xd", method = RequestMethod.POST, consumes = { "multipart/form-data" })
    public void saveSermon3(@RequestPart Sermon sermon, @RequestPart(value = "image", required = false) MultipartFile image,
                            @RequestPart(value = "audio", required = false) MultipartFile audio) throws IOException {
        Sermon savedSermon = sermonService.saveSermon(sermon);
        System.out.println("works!");
        if(image != null) {
            System.out.println("image found");
            String photoName = StringUtils.cleanPath(image.getOriginalFilename());
            sermon.setPhotoUrl(photoName);
            sermonService.updateSermon(sermon);
            String uploadPhotoDir = "sermon-photo/" + savedSermon.getId();
            FileUploadUtil.saveFile(uploadPhotoDir, photoName, image);

            BufferedImage originalImage = ImageIO.read(new File(String.valueOf(Paths.get("sermon-photo/" + savedSermon.getId() + "/" + photoName))));
            int targetWidth = 50;
            int targetHeight = originalImage.getHeight() / (originalImage.getWidth() / targetWidth);

            BufferedImage resizedImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_RGB);
            Graphics2D graphics2D = resizedImage.createGraphics();
            graphics2D.drawImage(originalImage, 0, 0, targetWidth, targetHeight, null);
            graphics2D.dispose();

            FileUploadUtil.saveFile(uploadPhotoDir, photoName.replace(".jpg", "_small.jpg"), resizedImage);
        }

        if(audio != null) {
            System.out.println("audio found");
            String audioName = StringUtils.cleanPath(audio.getOriginalFilename());
            sermon.setAudioUrl(audioName);
            sermon.setUrl("http://localhost:8080/api/sermon/" + savedSermon.getId() + "/audio");
            sermonService.updateSermon(sermon);

            String uploadAudioDir = "sermon-audio/" + savedSermon.getId();
            FileUploadUtil.saveFile(uploadAudioDir, audioName, audio);
        }
    }

    @GetMapping("/api/sermons/main")
    public List<Sermon> getTwoSermons() {
        List<Sermon> list = sermonService.retrieveSermons();
        int minIndex = Math.min(0, list.size());
        int maxIndex = Math.min(2, list.size());
        return list.subList(minIndex, maxIndex);
    }

    @DeleteMapping("/api/sermons/{id}")
    public void deleteSermon(@PathVariable(name = "id") Long sermonId) {
        sermonService.deleteSermon(sermonId);
        System.out.println("Sermon " + sermonId + " has been deleted");
    }

    @PutMapping("/api/sermons/{id}")
    public void updateSermon(@RequestBody Sermon sermon, @PathVariable(name = "id") Long sermonId) {
        Sermon serm = sermonService.getSermon(sermonId);
        if (serm != null)
            sermonService.updateSermon(sermon);

    }
}
