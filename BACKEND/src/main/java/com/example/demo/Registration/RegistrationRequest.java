package com.example.demo.Registration;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class RegistrationRequest {
    private String username;
    private String fullName;
    private String region;
    private String phoneNumber;
    private String email;
    private String password;
}