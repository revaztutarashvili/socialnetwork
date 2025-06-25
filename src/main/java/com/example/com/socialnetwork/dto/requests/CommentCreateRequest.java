package com.example.com.socialnetwork.dto.requests;


public record CommentCreateRequest(
        String commentText,
        Long authorId, // კომენტარის ავტორის ID
        Long postId // პოსტის ID, რომელზეც იწერება კომენტარი
) {}