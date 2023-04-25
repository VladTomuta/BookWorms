package com.example.demo.DTO;

public record UserDTO (
        String username,
        String fullName,
        String phoneNumber,
        String email,
        String region,
        String password
){
}