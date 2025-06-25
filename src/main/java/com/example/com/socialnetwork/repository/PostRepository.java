package com.example.com.socialnetwork.repository;

import com.example.com.socialnetwork.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByAuthorId(Long userId);
}