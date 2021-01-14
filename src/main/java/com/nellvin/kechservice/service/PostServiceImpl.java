package com.nellvin.kechservice.service;

import com.nellvin.kechservice.model.Post;
import com.nellvin.kechservice.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Override
    public List<Post> retrievePosts() {
//        return postRepository.findAll().sort((fst, sec) -> sec.getId().compareTo(fst.getId()));
        List<Post> list = postRepository.findAll();
        Collections.sort(list, Comparator.comparing(Post::getCreateDate));
        Collections.reverse(list);
        return list;
    }

    @Override
    public Post getPost(Long postId) {
        return postRepository.findById(postId).get();
    }

    @Override
    public Post savePost(Post post) {
        postRepository.save(post);
        return post;
    }

    @Override
    public void deletePost(Long postId) {
        postRepository.deleteById(postId);
    }

    @Override
    public void updatePost(Post post) {
        postRepository.save(post);
    }
}
