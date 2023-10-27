package com.plantapp.plantapp.post.service;

import com.plantapp.plantapp.post.model.Post;
import com.plantapp.plantapp.post.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService implements IPostService {

    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }
    @Override
    public Optional<Post> getPostById(int postId) {
        return postRepository.findById(postId);
    }
    @Override
    public Post addNewPost(Post post) {
        return postRepository.save(post);
    }

    @Override
    public void deletePostById(int postId) {
        postRepository.deleteById(postId);
    }
}
