package com.example.demo.Repository;

import com.example.demo.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@EnableJpaRepositories
@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

    User findOneByEmailAndPassword(String email, String password);
    Optional<User> findOneByPhoneNumberAndPassword(String phoneNumber, String password);

    User findByPhoneNumber(String phoneNumber);
    User findByEmail(String email);

    Optional<User> findOneByEmail(String email);
}
