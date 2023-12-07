package com.plantapp.plantapp.post.service;

import com.plantapp.plantapp.post.model.Post;
import com.plantapp.plantapp.post.model.PostDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class PostDTOMapper implements IPostDTOMapper {
    @Override
    public List<PostDTO> getShorterPost(List<Post> posts) {
        return posts.stream()
                .map(post -> new PostDTO(post.getId(), post.getTitle(), post.getLead(), post.getDate()))
                .collect(Collectors.toList());
    }
}
