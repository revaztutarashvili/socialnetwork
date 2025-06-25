package com.example.com.socialnetwork.repository;


import com.example.com.socialnetwork.entity.Post;
import com.example.com.socialnetwork.entity.PostLike;
import com.example.com.socialnetwork.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface PostLikeRepository extends JpaRepository<PostLike, Long> {
    Optional<PostLike> findByUserAndPost(User user, Post post);
    void deleteByUserAndPost(User user, Post post);
}