package com.example.demo.Service;

import com.example.demo.Entity.User;
import com.example.demo.Repository.BookRepository;
import com.example.demo.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User addUser(User user){
        return userRepository.saveAndFlush(user);
    }

    public User getUser(int id){
        return userRepository.getById(id);
    }

}