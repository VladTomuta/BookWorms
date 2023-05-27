package com.example.demo.DTO;

public record FullUserDTO(
        Integer user_id,
        String username,
        String fullName,
        String phoneNumber,
        String email,
        String region,
        String password,
        String role
){
}