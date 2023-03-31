package com.example.demo.DTO;

public record UserDTO (
        Integer userId,
        String username,
        String fullName,
        String phoneNumber,
        String email
){
}