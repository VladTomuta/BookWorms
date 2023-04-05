package com.example.demo.Entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
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

    @ManyToMany
    @JoinTable(name = "user_book",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn( name =  "book_id"))
    private Set<Book> booksIOwn = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_user_id", referencedColumnName = "user_id")
    private List<ProfileReview> profileReviews;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_user_id", referencedColumnName = "user_id")
    private List<BookReview> bookReviews;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}