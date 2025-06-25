package com.example.com.socialnetwork.dto.requests;
/**
 * ეს record გამოიყენება ახალი კომენტარის შესაქმნელად.
 * კლიენტმა უნდა გადმოსცეს კომენტარის ტექსტი, ავტორის ID და იმ პოსტის ID, რომელზეც კომენტარი იწერება.
 * `authorId`-ზე ვრცელდება იგივე უსაფრთხოების შენიშვნა, რაც `PostCreateRequest`-ზე.
 */

public record CommentCreateRequest(
        String commentText,
        Long authorId, // კომენტარის ავტორის ID
        Long postId // პოსტის ID, რომელზეც იწერება კომენტარი
) {}