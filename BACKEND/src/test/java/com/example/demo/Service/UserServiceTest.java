package com.example.demo.Service;

import com.example.demo.DTOMapper.UserDTOMapper;
import com.example.demo.Entity.User;
import com.example.demo.Repository.UserRepository;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.aspectj.lang.annotation.After;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith({MockitoExtension.class})
class UserServiceTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private UserDTOMapper userDTOMapper;
    private UserService userService;
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


    @BeforeEach
    void setUp() {
        userService = new UserService(userRepository, userDTOMapper);
    }


    @Test
    void addUser() {
        //given
        User user = new User(
                "Vlad",
                "Vlad Tomuta",
                "Timis",
                "0755069911",
                "vlad.tomuta@student.upt.ro",
                passwordEncoder.encode("abc"),
                "USER"
        );
        //when
        userService.addUser(user);

        //then
        ArgumentCaptor<User> userArgumentCaptor = ArgumentCaptor.forClass(User.class);

        verify(userRepository).saveAndFlush(userArgumentCaptor.capture());

        User capturedUser = userArgumentCaptor.getValue();
        assertThat(capturedUser).isEqualTo(user);
    }

    @Test
    void getAllUsers() {
        //when
        userService.getAllUsers();

        //then
        verify(userRepository).findAll();
    }

    @Test
    void deleteUser() {
        //given
        User user = new User(
                "Vlad",
                "Vlad Tomuta",
                "Timis",
                "0755069911",
                "vlad.tomuta@student.upt.ro",
                passwordEncoder.encode("abc"),
                "USER"
        );

        given(userRepository.findById(user.getUser_id())).willReturn(Optional.of(user));

        //when
        userService.deleteUser(user.getUser_id());

        //then
        ArgumentCaptor<Integer> userIdArgumentCaptor = ArgumentCaptor.forClass(int.class);

        verify(userRepository).deleteById(userIdArgumentCaptor.capture());

        int capturedUserId = userIdArgumentCaptor.getValue();
        assertThat(capturedUserId).isEqualTo(user.getUser_id());
    }
}