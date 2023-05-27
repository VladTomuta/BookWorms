package com.example.demo.DTO;

import com.example.demo.Entity.Role;

public record FullUserDTO(
        Integer user_id,
        String username,
        String fullName,
        String phoneNumber,
        String email,
        String region,
        String password,
        Role role
){
}