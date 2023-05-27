package com.example.demo.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "books")
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id", columnDefinition = "INTEGER")
    private int book_id;
    @Column(name = "title", columnDefinition = "VARCHAR(255)")
    private String title;
    @Column(name = "author", columnDefinition = "VARCHAR(255)")
    private String author;
    @Column(name = "genre", columnDefinition = "VARCHAR(255)")
    private String genre;

    public Book(String title, String author, String genre) {
        this.title = title;
        this.author = author;
        this.genre = genre;
    }

    @ManyToMany
    private Set<User> ownersOfTheBook = new HashSet<>();

    @OneToMany(mappedBy = "addressed_to_id", cascade = CascadeType.ALL)
    private List<BookReview> bookReviews;
}
