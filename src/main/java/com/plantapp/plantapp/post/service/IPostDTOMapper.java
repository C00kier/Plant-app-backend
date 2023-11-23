package com.plantapp.plantapp.post.service;

import com.plantapp.plantapp.post.model.Post;
import com.plantapp.plantapp.post.model.PostDTO;

import java.util.List;

public interface IPostDTOMapper {
    List<PostDTO> getShorterPost(List<Post> posts);
}
