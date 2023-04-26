package com.example.demo.DTO;

public record SignupDTO (
        String username,
        String fullName,
        String phoneNumber,
        String email,
        String region,
        String password
){
}
