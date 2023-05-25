package com.example.demo.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "rent")
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class Rent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int rent_id;
    @Column(name = "renter_by_id", columnDefinition = "INTEGER")
    private int rented_by_id;
    @Column(name = "renter_id", columnDefinition = "INTEGER")
    private int renter_id;
    @Column(name = "book_id", columnDefinition = "INTEGER")
    private int book_id;
    @Column(name = "date_of_rental", columnDefinition = "DATE")
    private LocalDate date_of_rental;
    @Column(name = "date_of_return", columnDefinition = "DATE")
    private LocalDate date_of_return;
    @Column(name = "status", columnDefinition = "VARCHAR(255)")
    private String status;
}