package com.example.demo.Web.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserRegistrationDTO {
    private String username;
    private String fullName;
    private String region;
    private String phoneNumber;
    private String email;
    private String password;
}
