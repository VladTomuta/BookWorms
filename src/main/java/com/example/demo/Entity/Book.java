package com.example.demo.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    @Column(name = "rented_by_id", columnDefinition = "INTEGER")
    private int Rented_by_id;
    @Column(name = "owner_id", columnDefinition = "INTEGER")
    private int Owner_id;
}
