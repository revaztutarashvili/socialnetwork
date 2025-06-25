package com.example.com.socialnetwork.controller;

import com.example.com.socialnetwork.dto.CommentDto;
import com.example.com.socialnetwork.dto.requests.CommentCreateRequest;
import com.example.com.socialnetwork.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<CommentDto> createComment(@RequestBody CommentCreateRequest request) {
        return new ResponseEntity<>(commentService.createComment(request), HttpStatus.CREATED);
    }

    @PutMapping("/{commentId}")
    public ResponseEntity<CommentDto> updateComment(@PathVariable Long commentId,
                                                    @RequestHeader("X-User-Id") Long requestingUserId,
                                                    @RequestBody Map<String, String> payload) {
        String newText = payload.get("commentText");
        return ResponseEntity.ok(commentService.updateComment(commentId, requestingUserId, newText));
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long commentId, @RequestHeader("X-User-Id") Long requestingUserId) {
        commentService.deleteComment(commentId, requestingUserId);
        return ResponseEntity.noContent().build();
    }
}
