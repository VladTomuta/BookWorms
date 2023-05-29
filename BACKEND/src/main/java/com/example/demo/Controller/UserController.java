package com.example.demo.Controller;

import com.example.demo.DTO.BookDTO;
import com.example.demo.DTO.LoginDTO;
import com.example.demo.DTO.SignupDTO;
import com.example.demo.DTO.UserDTO;
import com.example.demo.DTO.FullUserDTO;
import com.example.demo.Entity.Role;
import com.example.demo.Entity.User;
import com.example.demo.Response.LoginResponse;
import com.example.demo.Response.SignupResponse;
import com.example.demo.Service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/users")
@CrossOrigin("http://localhost:3000")
@Tag(name = "User")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Operation(summary = "Add a user")
    @PostMapping("/addUser")
    public FullUserDTO addUser(@RequestBody SignupDTO signupDTO){

        User user = new User(
                signupDTO.username(),
                signupDTO.fullName(),
                signupDTO.region(),
                signupDTO.phoneNumber(),
                signupDTO.email(),
                this.passwordEncoder.encode(signupDTO.password()),
                Role.USER
        );

        return userService.addUser(user);
    }

    @Operation(summary = "Get a user given by id")
    @GetMapping("/getUser/{id}")
    public UserDTO getUser(@PathVariable int id){
        return userService.getUser(id);
    }

    @Operation(summary = "Get all users")
    @GetMapping("/getAllUsers")
    public Set<FullUserDTO> getUser(){
        return userService.getAllUsers();
    }

    @Operation(summary = "Update a user given by id")
    @PutMapping("/updateBook/{id}")
    public FullUserDTO updateBook(@PathVariable int id, @RequestBody FullUserDTO fullUserDTO){

        User user = new User(
                fullUserDTO.username(),
                fullUserDTO.fullName(),
                fullUserDTO.region(),
                fullUserDTO.phoneNumber(),
                fullUserDTO.email(),
                fullUserDTO.password(),
                fullUserDTO.role()
        );

        return userService.updateUser(id, user);
    }

    @Operation(summary = "Delete a user given by id")
    @DeleteMapping("/deleteUser/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable int id){
        return userService.deleteUser(id);
    }

    @Operation(summary = "Get all books a user owns")
    @GetMapping("/booksOwned/{id}")
    public Set<BookDTO> getBooksOwnedById(@PathVariable int id){
        return userService.getBooksOwnedById(id);
    }

    @Operation(summary = "Login a user")
    @PostMapping(path = "/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginDTO loginDTO) {
        LoginResponse loginResponse = userService.loginUser(loginDTO);
        return ResponseEntity.ok(loginResponse);
    }

    @Operation(summary = "Sign up a user")
    @PostMapping(path = "/signup")
    public ResponseEntity<?> signupUser(@RequestBody SignupDTO signupDTO) {
        SignupResponse signupResponse = userService.signupUser(signupDTO);
        return ResponseEntity.ok(signupResponse);
    }

    @Operation(summary = "Get all books not owned by a user")
    @GetMapping("/getAllBooksNotOwned/{id}")
    public Set<BookDTO> getAllBooksNotOwned(@PathVariable int id) {return userService.getAllBooksNotOwned(id);}

    @Operation(summary = "Get role of user (in order to check if the user is allowed on that url or not)")
    @GetMapping("/getRoleOfUser/{id}")
    public Role getRoleOfUser(@PathVariable int id) {return userService.getRoleOfUser(id);}

    @Operation(summary = "Regenerate the jwt token of a user")
    @GetMapping("/regenerateUserToken/{id}")
    public String regenerateUserToken(@PathVariable int id) {return userService.regenerateUserToken(id);}
}