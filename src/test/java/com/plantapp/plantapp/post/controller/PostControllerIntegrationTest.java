package com.plantapp.plantapp.post.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.plantapp.plantapp.post.model.Post;
import com.plantapp.plantapp.post.repository.PostRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.time.LocalDate;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "classpath:application-test.properties")
@AutoConfigureMockMvc
class PostControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private PostRepository postRepository;


    @Test
    void addNewPost() throws Exception {
        Post testPost = new Post();
        testPost.setTitle("test title");
        testPost.setLead("test lead");
        testPost.setArticle("test article");
        testPost.setDate(LocalDate.now().toString());

        ResultActions resultActions = mockMvc.perform(post("/post/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(testPost)));
        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title", is(testPost.getTitle())));
    }

    @Test
    void getAllPosts() throws Exception {
        mockMvc.perform(get("/post/getAllPosts"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(greaterThanOrEqualTo(0))));
    }

    @Test
    void getPostById() throws Exception {
        createPost();
        Post existingPost = getExistingPost();
        mockMvc.perform(get("/post/{post-id}", existingPost.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title", is(existingPost.getTitle())));
    }

    @Test
    void deletePostById() throws Exception {
        Post existingPost = getExistingPost();
        mockMvc.perform(delete("/post/delete/{post-id}", existingPost.getId()))
                .andExpect(status().isOk());

        assertFalse(postRepository.findById(existingPost.getId()).isPresent());
    }

    private void createPost() throws Exception {
        Post testPost = new Post();
        testPost.setTitle("test title");
        testPost.setLead("test lead");
        testPost.setArticle("test article");
        testPost.setDate(LocalDate.now().toString());

        ResultActions resultActions = mockMvc.perform(post("/post/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(testPost)));
        resultActions
                .andExpect(status().isOk());
    }
    private Post getExistingPost() throws Exception {
        ResultActions resultActions = mockMvc.perform(get("/post/getAllPosts"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(greaterThanOrEqualTo(1))));
        String content = resultActions.andReturn().getResponse().getContentAsString();
        String post = content.substring(content.indexOf("{"), content.indexOf("}") + 1);
        System.out.println(post);
        return objectMapper.readValue(post, Post.class);
    }
}