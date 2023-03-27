package com.example.demo.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@Entity(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int user_id;
    @Column(name = "username", columnDefinition = "VARCHAR(255)")
    private String username;

    @Column(name = "fullName", columnDefinition = "VARCHAR(255)")
    private String fullName;

    @Column(name = "region", columnDefinition = "VARCHAR(255)")
    private String region;

    @Column(name = "phoneNumber", columnDefinition = "VARCHAR(255)")
    private String phoneNumber;

    @Column(name = "email", columnDefinition = "VARCHAR(255)")
    private String email;

    @Column(name = "password", columnDefinition = "VARCHAR(255)")
    private String password;

    @ManyToMany
    @JoinTable(name = "user_book",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn( name =  "book_id"))
    private Set<Book> booksIOwn = new HashSet<>();
}
