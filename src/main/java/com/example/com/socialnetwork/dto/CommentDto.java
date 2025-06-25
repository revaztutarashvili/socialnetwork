package com.example.com.socialnetwork.dto;
/**
 *
 * */
import java.time.LocalDateTime;

    public record CommentDto(
            Long id,
            String commentText,
            String authorUsername,
            LocalDateTime createdAt,
            LocalDateTime updatedAt
    ) {}
