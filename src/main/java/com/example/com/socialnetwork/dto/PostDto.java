package com.example.com.socialnetwork.dto;
/**
 * - `authorUsername`: ჩვენ არ ვაბრუნებთ მთლიან User ობიექტს, არამედ მხოლოდ მის სახელს, რაც კლიენტს სჭირდება.
 * - `comments`: ვაბრუნებთ დაკავშირებული კომენტარების სიას, რომლებიც ასევე DTO-შია გადაყვანილი (`CommentDto`).
 * - `likeCount`: ვაბრუნებთ ლაიქების რაოდენობას. ეს არის გამოთვლილი ველი, რომელიც Post Entity-ში პირდაპირ არ წერია.
 * მის მნიშვნელობას სერვისის ლეიერში ავიღებთ `post.getLikes().size()`-ით.
 *  ამ სტრუქტურის ასაწყობად საჭიროა "Mapper" კომპონენტი, რომელიც Post Entity-დან შექმნის PostDto-ს.
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