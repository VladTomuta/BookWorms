package com.example.demo.Entity;

import jakarta.persistence.*;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "book_reviews")
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class BookReview {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int review_id;
    @Column(name = "written_by_id", columnDefinition = "INTEGER")
    private int written_by_id;
    @Column(name = "description", columnDefinition = "VARCHAR(255)")
    private String description;
    @Column(name = "rating", columnDefinition = "NUMERIC")
    private float rating;
    @Column(name = "addressed_to_id", columnDefinition = "INTEGER")
    private int addressed_to_id;
    @Column(name = "date_of_review", columnDefinition = "DATE")
    private LocalDate date_of_review;
}