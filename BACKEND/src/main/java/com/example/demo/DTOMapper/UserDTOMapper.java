package com.example.demo.DTOMapper;

import com.example.demo.DTO.UserDTO;
import com.example.demo.Entity.User;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class UserDTOMapper implements Function<User, UserDTO> {
    @Override
    public UserDTO apply(User user) {
        return new UserDTO(
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
