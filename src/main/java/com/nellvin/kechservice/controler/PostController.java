package com.nellvin.kechservice.controler;

//import com.nellvin.kechservice.model.FormWrapper;
import com.nellvin.kechservice.model.Post;
import com.nellvin.kechservice.service.PostService;
import com.nellvin.kechservice.utils.FileUploadUtil;
import org.apache.juli.logging.Log;
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
public class PostController {
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
        return (postService.retrievePosts().size() - 1) / POSTS_PER_PAGE;
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
        File file = new File(String.valueOf(Paths.get("post-file/" + sermonId + "/" + postService.getPost(sermonId).getFilePath())));
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
    public void saveSermonAndFile( Post post, @RequestParam("image") MultipartFile multipartFile) throws IOException {

        if (!multipartFile.isEmpty()) {
            System.out.println("REST multipart empty? :"+multipartFile.isEmpty());
            String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
            post.setFilePath(fileName);
            Post savedPost = postService.savePost(post);
            String uploadPhotoDir = "post-file/" + savedPost.getId();
            FileUploadUtil.saveFile(uploadPhotoDir, fileName, multipartFile);
        }else {
//            System.out.println("REST multipart is null");
            postService.savePost(post);
        }

    }


//    @RequestMapping(path = "/api/multi", method = RequestMethod.POST, consumes = {"multipart/form-data"})
////    @PostMapping(path = "/api/multi")
//    public void multipart(@RequestPart FormWrapper post) throws IOException {
//
//        System.out.println(post.title);
//        System.out.println(post.content);
//            System.out.println("REST multipart empty? :"+post.image.isEmpty());
//            String fileName = StringUtils.cleanPath(post.image.getOriginalFilename());
////            post.setFilePath(fileName);
//            Post savedPost = postService.savePost(new Post());
//            String uploadPhotoDir = "post-file/" + savedPost.getId();
//            FileUploadUtil.saveFile(uploadPhotoDir, fileName, post.image);
//
//    }

//    @RequestMapping(path = "/api/multi/title", method = RequestMethod.POST, consumes = {"multipart/form-data"})
//    @PostMapping(path = "/api/multi/title")
//    public void multipartTitle(@ModelAttribute FormWrapper post) throws IOException {
//
//        System.out.println(post);
//        System.out.println(post.title);
////        System.out.println(post.content);
////        System.out.println("REST multipart empty? :"+post.image.isEmpty());
////        String fileName = StringUtils.cleanPath(post.image.getOriginalFilename());
//////            post.setFilePath(fileName);
////        Post savedPost = postService.savePost(new Post());
////        String uploadPhotoDir = "post-file/" + savedPost.getId();
////        FileUploadUtil.saveFile(uploadPhotoDir, fileName, post.image);
//
//    }
    @RequestMapping(value = "/api/G", method = RequestMethod.POST, consumes = { "multipart/form-data" })
    public Post GSSwain(@RequestPart("post") Post post,
                        @RequestPart(value = "image", required = false) MultipartFile image) throws IOException {
        if (image!=null) {
            System.out.println("REST multipart empty? :"+image.isEmpty());
            String fileName = StringUtils.cleanPath(image.getOriginalFilename());
            post.setFilePath(fileName);
            Post savedPost = postService.savePost(post);
            String uploadPhotoDir = "post-file/" + savedPost.getId();
            FileUploadUtil.saveFile(uploadPhotoDir, fileName, image);
        }else {
            postService.savePost(post);
        }

        return post;
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
            postService.updatePost(post);

    }

}
