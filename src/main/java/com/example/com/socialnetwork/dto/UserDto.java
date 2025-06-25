package com.example.com.socialnetwork.dto;
/**
 * ეს record გამოიყენება მომხმარებლის შესახებ ინფორმაციის კლიენტისთვის დასაბრუნებლად.
 * Entity-დან DTO-ში კონვერტაციის შემდეგ, კლიენტი იღებს ზუსტად ამ სტრუქტურას.
 * ის შეიცავს 'id'-ს, რადგან კლიენტს შეიძლება დასჭირდეს ის შემდგომი მოქმედებებისთვის.
 * არ შეიცავს სენსიტიურ ინფორმაციას (მაგ. პაროლს, რომელიც ჩვენს User Entity-ში არც გვაქვს).
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