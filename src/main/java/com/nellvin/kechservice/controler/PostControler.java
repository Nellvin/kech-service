package com.nellvin.kechservice.controler;

import com.nellvin.kechservice.model.Post;
import com.nellvin.kechservice.service.PostService;
import com.nellvin.kechservice.utils.FileUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
public class PostControler {

    @Autowired
    private PostService postService;

    public void setPostService(PostService postService) {
        this.postService = postService;
    }

    @CrossOrigin
    @GetMapping("/api/posts")
    public List<Post> getAllSermons(){
        return postService.retrievePosts();
    }

    @GetMapping("/api/posts/{id}")
    public Post getSermonById(@PathVariable(value = "id") Long postId){
        return postService.getPost(postId);
    }

    @PostMapping("/api/posts")
    public void saveSermon(@RequestBody Post post){
        postService.savePost(post);
        System.out.println("Post saved!");
    }

    @PostMapping("/api/posts_file")
    public void saveSermonAndFile(Post post, @RequestParam("image") MultipartFile multipartFile) throws IOException {

        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        post.setFilePath(fileName);


        Post savedPost= postService.savePost(post);

        String uploadPhotoDir = "post-file/" + savedPost.getId();


        FileUploadUtil.saveFile(uploadPhotoDir, fileName, multipartFile);

    }

    @DeleteMapping("/api/posts/{id}")
    public void deleteSermon(@PathVariable(name="id")Long postId){
        postService.deletePost(postId);
        System.out.println("Post "+ postId+" has been deleted");
    }

    @PutMapping("/api/posts/{id}")
    public void updateSermon(@RequestBody Post post, @PathVariable(name = "id")Long postId){
        Post pos = postService.getPost(postId);
        if(pos != null)
            postService.updatePost(pos);

    }

}
