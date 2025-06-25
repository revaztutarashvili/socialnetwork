package com.example.com.socialnetwork.dto;
/**
 ** ეს record გამოიყენება კომენტარის შესახებ ინფორმაციის კლიენტისთვის დასაბრუნებლად.
 *  `PostDto`-ს მსგავსად, აქაც ავტორის მთლიანი ობიექტის ნაცვლად ვაბრუნებთ მხოლოდ `authorUsername`-ს.*
 * */
import java.time.LocalDateTime;

    public record CommentDto(
            Long id,
            String commentText,
            String authorUsername,
            LocalDateTime createdAt,
            LocalDateTime updatedAt
    ) {}
