package com.example.com.socialnetwork.controller;



import com.example.com.socialnetwork.dto.PostDto;
import com.example.com.socialnetwork.dto.requests.PostCreateRequest;
import com.example.com.socialnetwork.dto.requests.PostUpdateRequest;
import com.example.com.socialnetwork.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping
    public ResponseEntity<PostDto> createPost(@RequestBody PostCreateRequest request) {
        return new ResponseEntity<>(postService.createPost(request), HttpStatus.CREATED);
    }

    // რედაქტირებისთვის, მომთხოვნის ID უნდა გადმოგვცენ
    @PutMapping("/{postId}")
    public ResponseEntity<PostDto> updatePost(@PathVariable Long postId,
                                              @RequestHeader("X-User-Id") Long requestingUserId,
                                              @RequestBody PostUpdateRequest request) {
        return ResponseEntity.ok(postService.updatePost(postId, requestingUserId, request));
    }

    // წაშლისთვის, მომთხოვნის ID უნდა გადმოგვცენ
    @DeleteMapping("/{postId}")
    public ResponseEntity<Void> deletePost(@PathVariable Long postId, @RequestHeader("X-User-Id") Long requestingUserId) {
        postService.deletePost(postId, requestingUserId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<PostDto>> getAllPosts() {
        return ResponseEntity.ok(postService.getAllPosts());
    }

    @GetMapping("/{postId}")
    public ResponseEntity<PostDto> getPostById(@PathVariable Long postId) {
        return ResponseEntity.ok(postService.getPostById(postId));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<PostDto>> getPostsByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(postService.getPostsByUserId(userId));
    }
}