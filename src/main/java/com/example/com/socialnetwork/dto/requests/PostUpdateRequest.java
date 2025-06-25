package com.example.com.socialnetwork.dto.requests;
/**
 * ეს record გამოიყენება არსებული პოსტის ტექსტის განახლებისთვის.
 * კლიენტს შეუძლია მხოლოდ ტექსტის შეცვლა, ამიტომ მხოლოდ `postText` ველი გვაქვს.
 * პოსტის ID, რომლის განახლებაც გვინდა, როგორც წესი, URL-ში გადმოიცემა (მაგ. PUT /posts/{postId}).
 */

public record PostUpdateRequest(
        String postText
) {}