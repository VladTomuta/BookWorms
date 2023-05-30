package com.example.demo.Service;

import com.example.demo.Config.JwtService;
import com.example.demo.DTO.*;
import com.example.demo.DTOMapper.BookDTOMapper;
import com.example.demo.DTOMapper.FullUserDTOMapper;
import com.example.demo.DTOMapper.UserDTOMapper;
import com.example.demo.Entity.Book;
import com.example.demo.Entity.Role;
import com.example.demo.Entity.User;
import com.example.demo.Exception.IncorrectIdException;
import com.example.demo.Repository.BookRepository;
import com.example.demo.Repository.UserRepository;
import com.example.demo.Response.LoginResponse;
import com.example.demo.Response.SignupResponse;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
@NoArgsConstructor
public class UserService {

    @Autowired
    private FullUserDTOMapper fullUserDTOMapper;
    @Autowired
    private UserDTOMapper userDTOMapper;
    @Autowired
    private BookDTOMapper bookDTOMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private JwtService jwtService;

    public UserService(UserRepository userRepository, FullUserDTOMapper fullUserDTOMapper, UserDTOMapper userDTOMapper) {
        this.userRepository = userRepository;
        this.fullUserDTOMapper = fullUserDTOMapper;
        this.userDTOMapper = userDTOMapper;
    }

    public FullUserDTO addUser(User user){
        return fullUserDTOMapper.apply(userRepository.saveAndFlush(user));
    }

    public UserDTO getUser(int id)  {
          return userRepository.findById(id)
                  .stream()
                  .findAny()
                  .map(userDTOMapper)
                  .orElseThrow(IncorrectIdException::new);
    }

    public Set<FullUserDTO> getAllUsers() {
        return  userRepository.findAll()
                .stream()
                .map(fullUserDTOMapper)
                .collect(Collectors.toSet());
    }

    public FullUserDTO updateUser(int id, User user) {
        if(userRepository.findById(id).isPresent()){
            User actualUser = userRepository.findById(id).get();
            if(user.getEmail() != null){
                if(userRepository.findByEmail(user.getEmail()) != null) {
                    actualUser.setEmail(user.getEmail());
                }
                throw new IncorrectIdException();
            }
            if(user.getPassword() != null){
                actualUser.setPassword(user.getPassword());
            }
            if(user.getFullName() != null){
                actualUser.setFullName(user.getFullName());
            }
            if(user.getPhoneNumber() != null){
                if(userRepository.findByPhoneNumber(user.getPhoneNumber()) != null) {
                    actualUser.setPhoneNumber(user.getPhoneNumber());
                }
                throw new IncorrectIdException();
            }
            if(user.getRegion() != null){
                actualUser.setRegion(user.getRegion());
            }
            return fullUserDTOMapper.apply(userRepository.saveAndFlush(user));
        }
        //return some exception.
        throw new IncorrectIdException();
    }
    public Set<BookDTO> getBooksOwnedById(int id) {
        if(userRepository.findById(id).isPresent()){
            User actualUser = userRepository.findById(id).get();
            return actualUser.getBooksIOwn()
                             .stream()
                             .map(bookDTOMapper)
                             .collect(Collectors.toSet());
        }
        throw new IncorrectIdException();
    }

    public Set<BookDTO> getAllBooksNotOwned(int userId) {


        Optional<User> user = userRepository.findById(userId);

        if(user.isPresent())
        {
            Set<BookDTO> ownerBooks = user.get().getBooksIOwn()
                    .stream()
                    .map(bookDTOMapper)
                    .collect(Collectors.toSet());

            Set<BookDTO> books = bookRepository.findAll()
                    .stream()
                    .map(bookDTOMapper)
                    .filter(Predicate.not(ownerBooks::contains))
                    .collect(Collectors.toSet());

            return books;
        }
        throw new IncorrectIdException();
    }

    public Role getRoleOfUser(int id) {
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()) {
            return user.get().getRole();
        }
        throw new IncorrectIdException();
    }

    public ResponseEntity<String> deleteUser(int id) {
        if(userRepository.findById(id).isPresent()){

            User user = userRepository.findById(id).get();

            Set<Book> userBooks = user.getBooksIOwn();

            for(Book  book: userBooks) {
                book.getOwnersOfTheBook().remove(user);
            }
            userBooks.clear();

            user.getBookReviews().clear();
            user.getProfileReviews().clear();

            userRepository.deleteById(id);
            return new ResponseEntity<String>("Sucsessfully deleted!", HttpStatus.OK);
        }
        return new ResponseEntity<String>("Can't find specified user!", HttpStatus.NOT_FOUND);
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
                        Role.USER
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

                var jwtToken = jwtService.generateToken(userInDatabase);

                return new LoginResponse(userInDatabase, true, jwtToken);

                //return userOptional.map(user -> new LoginResponse(user, true)).orElseGet(() -> new LoginResponse(null, false));
            } else {

                return new LoginResponse(null, false, "");
            }
        }else {
            return new LoginResponse(null, false, "");
        }
    }

    public String regenerateUserToken(int id) {

        Optional<User> user = userRepository.findById(id);

        if(user.isPresent()) {
            return jwtService.generateToken(user.get());
        }

        throw new IncorrectIdException();
    }
}