package com.example.demo.Repository;

import com.example.demo.Entity.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.FactoryBasedNavigableListAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @AfterEach
    void tearDown() {
        userRepository.deleteAll();
    }


    @Test
    void itShouldCheckIfUserPhoneNumberExists() {
        //given
        String phoneNumber = "0755069911";
        User user = new User(
                "Vlad",
                "Vlad Tomuta",
                "Timis",
                phoneNumber,
                "vlad.tomuta@student.upt.ro",
                passwordEncoder.encode("abc"),
                "USER"
        );
        userRepository.save(user);

        //when
        User userInDatabase = userRepository.findByPhoneNumber(phoneNumber);

        //then
        assertEquals(userInDatabase, user);
    }

    @Test
    void itShouldCheckIfUserEmailExists() {
        String email = "vlad.tomuta@student.upt.ro";
        User user = new User(
                "Vlad",
                "Vlad Tomuta",
                "Timis",
                "0755069911",
                email,
                passwordEncoder.encode("abc"),
                "USER"
        );
        userRepository.save(user);

        //when
        User userInDatabase = userRepository.findByEmail(email);

        //then
        assertEquals(userInDatabase, user);
    }
}