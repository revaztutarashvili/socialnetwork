package com.example.com.socialnetwork.dto.requests;
/**
 * ეს record გამოიყენება ახალი პოსტის შესაქმნელად.
 * კლიენტმა უნდა გადმოსცეს პოსტის ტექსტი და ავტორის ID.
 */

public record PostCreateRequest(
        String postText,
        Long authorId // ჯერჯერობით (JWT)უსაფრთხოების გარეშე, ავტორის ID-ს პირდაპირ ვიღებთ
) {}