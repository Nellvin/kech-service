package com.nellvin.kechservice.controler;

import com.nellvin.kechservice.model.Post;
import com.nellvin.kechservice.service.PostService;
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
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
public class PostControler {
    private final int POSTS_PER_PAGE = 6;

    @Autowired
    private PostService postService;

    public void setPostService(PostService postService) {
        this.postService = postService;
    }

    @CrossOrigin
    @GetMapping("/api/posts")
    public List<Post> getAllPosts() {
        return postService.retrievePosts();
    }

    @GetMapping("/api/posts/pages")
    public int getPostPageCount() {
        return (postService.retrievePosts().size()-1) / POSTS_PER_PAGE;
    }

    @GetMapping("/api/posts2")
    public List<Post> getAllPosts2(@RequestParam Optional<Integer> page) {

        int requestedPage = page.orElse(0);

        List<Post> list = postService.retrievePosts();
        int pagesCount = list.size() / POSTS_PER_PAGE;

        if (requestedPage > pagesCount) {
            requestedPage = pagesCount;
        }
        int firstPost = requestedPage * POSTS_PER_PAGE;
        int lastPost = Math.min((requestedPage + 1) * POSTS_PER_PAGE, list.size());

        return list.subList(firstPost, lastPost);
    }

    @GetMapping("/api/posts/main")
    public List<Post> getThreePosts() {
        List<Post> list = postService.retrievePosts();
        int minIndex = Math.min(0, list.size());
        int maxIndex = Math.min(2, list.size());
        return list.subList(minIndex, maxIndex);
    }

    @GetMapping("/api/posts/{id}")
    public Post getSermonById(@PathVariable(value = "id") Long postId) {
        return postService.getPost(postId);
    }

    @GetMapping("/api/posts/{id}/image")
    public ResponseEntity<Resource> getImage(@PathVariable(value = "id") Long sermonId) throws FileNotFoundException {
        File file = new File(String.valueOf(Paths.get("post-file/" + sermonId +"/"+postService.getPost(sermonId).getFilePath())));
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=image.jpg");

        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(file.length())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }

    @PostMapping("/api/posts")
    public void saveSermon(@RequestBody Post post) {
        postService.savePost(post);
        System.out.println("Post saved!");
    }

    @PostMapping("/api/posts_file")
    public void saveSermonAndFile(Post post, @RequestParam("image") MultipartFile multipartFile) throws IOException {

        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        post.setFilePath(fileName);
        Post savedPost = postService.savePost(post);
        String uploadPhotoDir = "post-file/" + savedPost.getId();
        FileUploadUtil.saveFile(uploadPhotoDir, fileName, multipartFile);

    }

    @DeleteMapping("/api/posts/{id}")
    public void deleteSermon(@PathVariable(name = "id") Long postId) {
        postService.deletePost(postId);
        System.out.println("Post " + postId + " has been deleted");
    }

    @PutMapping("/api/posts/{id}")
    public void updateSermon(@RequestBody Post post, @PathVariable(name = "id") Long postId) {
        Post pos = postService.getPost(postId);
        if (pos != null)
            postService.updatePost(pos);

    }

}
