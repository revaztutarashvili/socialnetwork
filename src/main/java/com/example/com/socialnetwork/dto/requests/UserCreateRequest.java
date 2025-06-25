package com.example.com.socialnetwork.dto.requests;
import java.time.LocalDate;
/**
 * ეს record (DTO) გამოიყენება ახალი მომხმარებლის რეგისტრაციისთვის.
 * ის განსაზღვრავს იმ მონაცემების სტრუქტურას, რომელიც კლიენტმა (მაგ. frontend-მა ან Postman-მა)
 * უნდა გამოაგზავნოს JSON ფორმატში, როდესაც /users ენდფოინთზე POST მოთხოვნას აკეთებს.
 * შენიშვნა: ნუ აქ არ ჩავწერე 'id' ველი, რადგან ID-ს ბაზა აგენერირებს ავტომატურად და კლიენტმა ის არ უნდა გადმოსცეს.
 */
    public record UserCreateRequest(
            String firstName,
            String lastName,
            String username,
            String email,
            String mobile,
            LocalDate birthDate
    ) {}
