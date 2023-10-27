package com.plantapp.plantapp.post.service;

import com.plantapp.plantapp.post.model.Post;

import java.util.List;
import java.util.Optional;

public interface IPostService {
    List<Post> getAllPosts();

    Optional<Post> getPostById(int postId);

    Post addNewPost(Post post);

    void deletePostById(int postId);
}
