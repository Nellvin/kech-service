package com.nellvin.kechservice.service;

import com.nellvin.kechservice.model.Post;

import java.util.List;

public interface PostService {

    public List<Post> retrievePosts();

    public Post getPost(Long postId);

    public Post savePost(Post post);

    public void deletePost(Long postId);

    public void updatePost(Post post);
}

