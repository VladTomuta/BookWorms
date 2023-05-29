package com.example.demo.Response;

import com.example.demo.DTO.ProfileReviewDTO;
import com.example.demo.DTO.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProfileReviewResponse {
    private ProfileReviewDTO profileReviewDTO;
    private UserDTO userDTO;
}
