package com.example.demo.Service;

import com.example.demo.DTO.BookDTO;
import com.example.demo.DTO.LoginDTO;
import com.example.demo.DTO.SignupDTO;
import com.example.demo.DTO.UserDTO;
import com.example.demo.DTOMapper.BookDTOMapper;
import com.example.demo.DTOMapper.UserDTOMapper;
import com.example.demo.Entity.User;
import com.example.demo.Exception.IncorrectIdException;
import com.example.demo.Repository.UserRepository;
import com.example.demo.Response.LoginResponse;
import com.example.demo.Response.SignupResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserDTOMapper userDTOMapper;
    @Autowired
    private BookDTOMapper bookDTOMapper;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

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

    public SignupResponse signupUser(SignupDTO signupDTO) {

        if(Objects.equals(signupDTO.email(), "") || Objects.equals(signupDTO.fullName(), "") || Objects.equals(signupDTO.username(), "")
                || Objects.equals(signupDTO.password(), "") || Objects.equals(signupDTO.phoneNumber(), "") || Objects.equals(signupDTO.region(), "")) {
            return new SignupResponse("All fields must be completed", false);
        }

        User alreadyExistingUser = userRepository.findByEmail(signupDTO.email());

        if(alreadyExistingUser == null) {
            String regex = "^07\\d{8}$";

            if(!Pattern.matches(regex, signupDTO.phoneNumber())) {
                return new SignupResponse("This is not a valid phone number!", false);
            }

            alreadyExistingUser = userRepository.findByPhoneNumber(signupDTO.phoneNumber());

            if(alreadyExistingUser == null) {

                User user = new User(
                        signupDTO.username(),
                        signupDTO.fullName(),
                        signupDTO.region(),
                        signupDTO.phoneNumber(),
                        signupDTO.email(),
                        this.passwordEncoder.encode(signupDTO.password()),
                        "USER"
                );

                addUser(user);
                return new SignupResponse("Account successfully registered", true);
            } else {
                return new SignupResponse("An account with this phone number is already registered!", false);
            }

        } else {
            return new SignupResponse("An account with this emailOrPhoneNumber address is already registered!", false);
        }

    }

    public LoginResponse loginUser(LoginDTO loginDTO) {
        User userInDatabase = userRepository.findByEmail(loginDTO.email());

        if(userInDatabase == null) {
            userInDatabase = userRepository.findByPhoneNumber(loginDTO.email());
        }

        if (userInDatabase != null) {
            String password = loginDTO.password();
            String encodedPassword = userInDatabase.getPassword();
            boolean isPwdRight = passwordEncoder.matches(password, encodedPassword);
            if (isPwdRight) {
                //User userOptional = userRepository.findOneByEmailAndPassword(loginDTO.emailOrPhoneNumber(), encodedPassword);
                return new LoginResponse(userInDatabase, true);

                //return userOptional.map(user -> new LoginResponse(user, true)).orElseGet(() -> new LoginResponse(null, false));
            } else {

                return new LoginResponse(null, false);
            }
        }else {
            return new LoginResponse(null, false);
        }
    }
}