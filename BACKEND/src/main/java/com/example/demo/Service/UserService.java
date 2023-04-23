package com.example.demo.Service;

import com.example.demo.DTO.BookDTO;
import com.example.demo.DTO.UserDTO;
import com.example.demo.DTOMapper.BookDTOMapper;
import com.example.demo.DTOMapper.UserDTOMapper;
import com.example.demo.Entity.User;
import com.example.demo.Exception.IncorrectIdException;
import com.example.demo.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserDTOMapper userDTOMapper;
    @Autowired
    private BookDTOMapper bookDTOMapper;
    @Autowired
    private UserRepository userRepository;

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

    public Set<UserDTO> getAllUsers() {
        return  userRepository.findAll()
                .stream()
                .map(userDTOMapper)
                .collect(Collectors.toSet());
    }

    public Set<BookDTO> getBooksOwnedById(int id) {
        if(userRepository.findById(id).isPresent()){
            User actualUser = userRepository.findById(id).get();
            return actualUser.getBooksIOwn().stream().map(bookDTOMapper).collect(Collectors.toSet());
        }
        throw new IncorrectIdException();
    }

    public ResponseEntity<String> deleteUser(int id) {
        if(userRepository.findById(id).isPresent()){
            userRepository.deleteById(id);
            return new ResponseEntity<String>("Sucsessfully deleted!", HttpStatus.OK);
        }

        return new ResponseEntity<String>("Can't find specified book!", HttpStatus.NOT_FOUND);
    }
}