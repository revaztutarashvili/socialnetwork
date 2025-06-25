package com.example.com.socialnetwork.service;

import com.example.com.socialnetwork.dto.PostDto;
import com.example.com.socialnetwork.dto.requests.PostCreateRequest;
import com.example.com.socialnetwork.dto.requests.PostUpdateRequest;
import com.example.com.socialnetwork.entity.Post;
import com.example.com.socialnetwork.entity.User;
import com.example.com.socialnetwork.mapper.DtoMapper;
import com.example.com.socialnetwork.repository.PostRepository;
import com.example.com.socialnetwork.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final DtoMapper dtoMapper;

    @Transactional
    public PostDto createPost(PostCreateRequest request) {
        User author = userRepository.findById(request.authorId())
                .orElseThrow(() -> new RuntimeException("Author not found with id: " + request.authorId()));

        Post post = new Post();
        post.setPostText(request.postText());
        post.setAuthor(author);

        Post savedPost = postRepository.save(post);
        return dtoMapper.toPostDto(savedPost);
    }

    @Transactional
    public PostDto updatePost(Long postId, Long requestingUserId, PostUpdateRequest request) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found with id: " + postId));

        // სიმულაცია: ვამოწმებთ, რომ პოსტს არედაქტირებს მისი ავტორი
        if (!post.getAuthor().getId().equals(requestingUserId)) {
            throw new RuntimeException("User with id " + requestingUserId + " cannot edit this post.");
        }

        post.setPostText(request.postText());
        post.setUpdatedAt(LocalDateTime.now());

        Post updatedPost = postRepository.save(post);
        return dtoMapper.toPostDto(updatedPost);
    }

    @Transactional
    public void deletePost(Long postId, Long requestingUserId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found with id: " + postId));

        // სიმულაცია: ვამოწმებთ, რომ პოსტს შლის მისი ავტორი
        if (!post.getAuthor().getId().equals(requestingUserId)) {
            throw new RuntimeException("User with id " + requestingUserId + " cannot delete this post.");
        }

        postRepository.delete(post);
    }

    public PostDto getPostById(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found with id: " + postId));
        return dtoMapper.toPostDto(post);
    }

    public List<PostDto> getAllPosts() {
        return postRepository.findAll().stream()
                .map(dtoMapper::toPostDto)
                .collect(Collectors.toList());
    }

    public List<PostDto> getPostsByUserId(Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new RuntimeException("User not found with id: " + userId);
        }
        return postRepository.findByAuthorId(userId).stream()
                .map(dtoMapper::toPostDto)
                .collect(Collectors.toList());
    }
}