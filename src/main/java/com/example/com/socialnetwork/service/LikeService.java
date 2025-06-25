package com.example.com.socialnetwork.service;


import com.example.com.socialnetwork.entity.Post;
import com.example.com.socialnetwork.entity.PostLike;
import com.example.com.socialnetwork.entity.User;
import com.example.com.socialnetwork.repository.PostLikeRepository;
import com.example.com.socialnetwork.repository.PostRepository;
import com.example.com.socialnetwork.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LikeService {

    private final PostLikeRepository likeRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    @Transactional
    public void likePost(Long postId, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found with id: " + postId));

        // თუ უკვე არსებობს ლაიქი, არაფერს ვაკეთებთ
        if (likeRepository.findByUserAndPost(user, post).isPresent()) {
            return;
        }

        PostLike like = new PostLike(user, post);
        likeRepository.save(like);
    }

    @Transactional
    public void unlikePost(Long postId, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found with id: " + postId));

        // თუ ლაიქი არ არსებობს, ვათროუებთ ექსეფშენს
        if (likeRepository.findByUserAndPost(user, post).isEmpty()) {
            throw new RuntimeException("Cannot unlike post that was not liked.");
        }

        likeRepository.deleteByUserAndPost(user, post);
    }
}