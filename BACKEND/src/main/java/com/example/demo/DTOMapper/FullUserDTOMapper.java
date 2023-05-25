package com.example.demo.DTOMapper;

import com.example.demo.DTO.FullUserDTO;
import com.example.demo.Entity.User;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class FullUserDTOMapper implements Function<User, FullUserDTO> {
    @Override
    public FullUserDTO apply(User user) {
        return new FullUserDTO(
                user.getUser_id(),
                user.getUsername(),
                user.getFullName(),
                user.getPhoneNumber(),
                user.getEmail(),
                user.getRegion(),
                user.getPassword(),
                user.getRole()
        );
    }
}
