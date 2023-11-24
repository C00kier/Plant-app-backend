package com.plantapp.plantapp.post.controller;

import com.plantapp.plantapp.post.model.Post;
import com.plantapp.plantapp.post.model.PostDTO;
import com.plantapp.plantapp.post.service.PostDTOMapper;
import com.plantapp.plantapp.post.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/post")
public class PostController {

    private final PostService postService;
    private final PostDTOMapper postDTOMapper;

    public PostController(PostService postService, PostDTOMapper postDTOMapper) {
        this.postService = postService;
        this.postDTOMapper = postDTOMapper;
    }

    @GetMapping("/getAllPosts")
    public ResponseEntity<List<PostDTO>> getAllPosts() {
        try {
            List<Post> posts = postService.getAllTitlesAndLeads();
            return ResponseEntity.ok(postDTOMapper.getShorterPost(posts));
        } catch (Exception error) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{post-id}")
    public ResponseEntity<Post> getPostById(@PathVariable("post-id") int postId) {
        try {
            Optional<Post> post = postService.getPostById(postId);
            return post.map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
        } catch (Exception error) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/add")
    public ResponseEntity<Post> addNewPost(@RequestBody Post post) {
        return ResponseEntity.ok(postService.addNewPost(post));
    }

    @DeleteMapping("/delete/{post-id}")
    public ResponseEntity<Object> deletePostById(@PathVariable("post-id") int postId) {
        postService.deletePostById(postId);
        return ResponseEntity.ok().build();
    }

}
