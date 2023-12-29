package com.plantapp.plantapp.post.controller;

import com.plantapp.plantapp.post.model.Post;
import com.plantapp.plantapp.post.model.PostDTO;
import com.plantapp.plantapp.post.service.PostDTOMapper;
import com.plantapp.plantapp.post.service.PostService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PostControllerTest {
    @Mock
    private PostService postService;

    @Mock
    private PostDTOMapper postDTOMapper;

    @InjectMocks
    private PostController postController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllPostsSuccess(){
        List<Post> posts = new ArrayList<>();
        List<PostDTO> postDTOs = new ArrayList<>();
        when(postService.getAllTitlesAndLeads()).thenReturn(posts);
        when(postDTOMapper.getShorterPost(posts)).thenReturn(postDTOs);

        ResponseEntity<List<PostDTO>> response = postController.getAllPosts();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(postDTOs, response.getBody());
        verify(postService, times(1)).getAllTitlesAndLeads();
        verify(postDTOMapper, times(1)).getShorterPost(posts);
    }

    @Test
    void testGetAllPostsException() {
        when(postService.getAllTitlesAndLeads()).thenThrow(new RuntimeException());
        ResponseEntity<List<PostDTO>> response = postController.getAllPosts();
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        verify(postService, times(1)).getAllTitlesAndLeads();
    }

    @Test
    void testGetPostByIdSuccess() {
        int postId = 13;
        Post mockPost = new Post();
        when(postService.getPostById(postId)).thenReturn(Optional.of(mockPost));

        ResponseEntity<Post> response = postController.getPostById(postId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockPost, response.getBody());
        verify(postService, times(1)).getPostById(postId);
    }

    @Test
    void testGetPostByIdInvalid() {
        int postId = 13;
        when(postService.getPostById(postId)).thenReturn(Optional.empty());
        ResponseEntity<Post> response = postController.getPostById(postId);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        verify(postService, times(1)).getPostById(postId);
    }

    @Test
    void testGetPostByIdException() {
        int postId = 13;
        when(postService.getPostById(postId)).thenThrow(new RuntimeException());
        ResponseEntity<Post> response = postController.getPostById(postId);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        verify(postService, times(1)).getPostById(postId);
    }

    @Test
    void testAddNewPost() {
        Post mockPost = new Post();
        when(postService.addNewPost(mockPost)).thenReturn(mockPost);
        ResponseEntity<Post> response = postController.addNewPost(mockPost);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockPost, response.getBody());
        verify(postService, times(1)).addNewPost(mockPost);
    }
    @Test
    void testDeletePostById() {
        int postId = 13;
        ResponseEntity<Object> response = postController.deletePostById(postId);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(postService, times(1)).deletePostById(postId);
    }
}