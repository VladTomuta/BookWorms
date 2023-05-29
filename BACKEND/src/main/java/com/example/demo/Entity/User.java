package com.example.demo.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder @Data
@Entity(name = "users")
public class User implements UserDetails {
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

    @Column(name = "role", columnDefinition = "VARCHAR(255)")
    @Enumerated(EnumType.STRING)
    private Role role;

    @ManyToMany
    @JoinTable(name = "user_book",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn( name =  "book_id"))
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnoreProperties("ownersOfTheBook")
    private Set<Book> booksIOwn = new HashSet<>();

    @OneToMany(mappedBy = "written_by_id", cascade = CascadeType.ALL)
    private List<ProfileReview> profileReviews;

    @OneToMany(mappedBy = "written_by_id", cascade = CascadeType.ALL)
    private List<BookReview> bookReviews;

    public User(String username, String fullName, String region, String phoneNumber, String email, String password, Role role) {
        this.username = username;
        this.fullName = fullName;
        this.region = region;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}