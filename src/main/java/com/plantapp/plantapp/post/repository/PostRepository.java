package com.plantapp.plantapp.post.repository;

import com.plantapp.plantapp.post.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {
    void deleteById(int postId);
}
