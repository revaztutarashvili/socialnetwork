package com.example.com.socialnetwork.dto.requests;


public record PostCreateRequest(
        String postText,
        Long authorId // უსაფრთხოების გარეშე, ავტორის ID-ს პირდაპირ ვიღებთ
) {}