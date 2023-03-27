package com.example.demo.Service;

import com.example.demo.DTO.UserDTO;
import com.example.demo.DTOMapper.UserDTOMapper;
import com.example.demo.Entity.User;
import com.example.demo.Exception.IncorrectIdException;
import com.example.demo.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class UserService {

    //@Autowired
    private UserDTOMapper userDTOMapper;
    @Autowired
    private UserRepository userRepository;

    public UserService(UserDTOMapper userDTOMapper) {
        this.userDTOMapper = userDTOMapper;
    }

    public UserDTO addUser(User user){
        return userDTOMapper.apply(userRepository.saveAndFlush(user));
    }

    public UserDTO getUser(int id)  {
          return userRepository.findById(id)
                  .stream()
                  .findAny()
                  .map(userDTOMapper)
                  .orElseThrow(IncorrectIdException::new);
    }

}