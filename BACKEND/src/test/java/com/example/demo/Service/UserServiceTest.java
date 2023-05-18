package com.example.demo.Service;

import com.example.demo.DTOMapper.UserDTOMapper;
import com.example.demo.Repository.UserRepository;
import org.aspectj.lang.annotation.After;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@ExtendWith({MockitoExtension.class})
class UserServiceTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private UserDTOMapper userDTOMapper;
    private UserService userService;


    @BeforeEach
    void setUp() {
        userService = new UserService(userRepository, userDTOMapper);
    }


    @Test
    @Disabled
    void addUser() {
    }

    @Test
    void getAllUsers() {
        //when
        userService.getAllUsers();

        //then
        verify(userRepository).findAll();
    }

    @Test
    @Disabled
    void deleteUser() {
    }
}