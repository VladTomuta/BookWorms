package com.example.demo.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "profile_reviews")
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class ProfileReview {
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