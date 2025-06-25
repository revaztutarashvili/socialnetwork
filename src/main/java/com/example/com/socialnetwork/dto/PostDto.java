package com.example.com.socialnetwork.dto;
/**
 *
 * */

import java.time.LocalDateTime;
import java.util.List;

public record PostDto(
        Long id,
        String postText,
        String authorUsername,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        List<CommentDto> comments,
        int likeCount
) {}