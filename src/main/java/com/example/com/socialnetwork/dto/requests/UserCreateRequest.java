package com.example.com.socialnetwork.dto.requests;
import java.time.LocalDate;
/**
 *
 * */
    public record UserCreateRequest(
            String firstName,
            String lastName,
            String username,
            String email,
            String mobile,
            LocalDate birthDate
    ) {}
