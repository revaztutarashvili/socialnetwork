package com.example.com.socialnetwork.dto;
/**
 *
 * */

import java.time.LocalDate;

public record UserDto(
        Long id,
        String firstName,
        String lastName,
        String username,
        String email,
        String mobile,
        LocalDate birthDate
) {}