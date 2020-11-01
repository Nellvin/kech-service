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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
//@RequestMapping("/api/v1")
public class SermonControler {

    @Autowired
    private SermonService sermonService;

    public void setSermonService(SermonService sermonService) {
        this.sermonService = sermonService;
    }

    @CrossOrigin
    @GetMapping("/api/sermons")
    public List<Sermon> getAllSermons(){
        return sermonService.retrieveSermons();
    }

    @GetMapping("/api/sermons/{id}")
    public Sermon getSermonById(@PathVariable(value = "id") Long sermonId){
        return sermonService.getSermon(sermonId);
    }

    @GetMapping("/api/sermon/{id}/image")
    public ResponseEntity<Resource> getImage(@PathVariable(value = "id") Long sermonId) throws FileNotFoundException {
        File file = new File(String.valueOf(Paths.get("sermon-photo/" + sermonId +"/"+sermonService.getSermon(sermonId).getPhoto())));
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=image.jpg");

        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(file.length())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }

    @GetMapping("/api/sermon/image")
    public ResponseEntity<Resource> getImage2() throws FileNotFoundException {
        File file = new File(String.valueOf(Paths.get("sermon-photo/1/portretProboscis_monkey_(Nasalis_larvatus)_male_head_0.jpg")));
        System.out.println(Paths.get("sermon-photo/1/portretProboscis_monkey_(Nasalis_larvatus)_male_head_0.jpg"));

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=image.jpg");

        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(file.length())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }

    @GetMapping("/api/sermon/{id}/audio")
    public ResponseEntity<Resource> getSermonAudio(@PathVariable(value = "id") Long sermonId) throws FileNotFoundException {
        File file = new File(String.valueOf(Paths.get("sermon-audio/" + sermonId +"/"+sermonService.getSermon(sermonId).getAudio())));
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
    public void saveSermon(@RequestBody Sermon sermon){
        sermonService.saveSermon(sermon);
        System.out.println("Sermon saved!");
    }

    @PostMapping("/api/sermons2")
    public void saveSermon2(Sermon sermon, @RequestParam("image") MultipartFile multipartFile1,
                            @RequestParam("music") MultipartFile multipartFile2) throws IOException {

        String photoName = StringUtils.cleanPath(multipartFile1.getOriginalFilename());
        sermon.setPhoto(photoName);

        String audioName = StringUtils.cleanPath(multipartFile2.getOriginalFilename());
        sermon.setAudio(audioName);

        Sermon savedSermon = sermonService.saveSermon(sermon);
        sermon.setUrl("http://localhost:8080/api/sermon/"+savedSermon.getId()+"/audio");
        sermonService.updateSermon(sermon);



        String uploadPhotoDir = "sermon-photo/" + savedSermon.getId();
        String uploadAudioDir = "sermon-audio/" + savedSermon.getId();


        FileUploadUtil.saveFile(uploadPhotoDir, photoName, multipartFile1);
        FileUploadUtil.saveFile(uploadAudioDir, audioName, multipartFile2);

//        return new RedirectView("/users", true);
    }

    @DeleteMapping("/api/sermons/{id}")
    public void deleteSermon(@PathVariable(name="id")Long sermonId){
        sermonService.deleteSermon(sermonId);
        System.out.println("Sermon "+ sermonId+" has been deleted");
    }

    @PutMapping("/api/sermons/{id}")
    public void updateSermon(@RequestBody Sermon sermon, @PathVariable(name = "id")Long sermonId){
        Sermon serm = sermonService.getSermon(sermonId);
        if(serm != null)
            sermonService.updateSermon(sermon);

    }
}
