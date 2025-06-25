package com.example.com.socialnetwork.controller;



import com.example.com.socialnetwork.dto.requests.LikeRequest;
import com.example.com.socialnetwork.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/posts/{postId}")
@RequiredArgsConstructor
public class LikeController {

    private final LikeService likeService;

    @PostMapping("/like")
    public ResponseEntity<Void> likePost(@PathVariable Long postId, @RequestBody LikeRequest request) {
        likeService.likePost(postId, request.userId());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/unlike")
    public ResponseEntity<Void> unlikePost(@PathVariable Long postId, @RequestBody LikeRequest request) {
        likeService.unlikePost(postId, request.userId());
        return ResponseEntity.noContent().build();
    }
}