package com.example.demo.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "rent")
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class Rent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int rentId;
    @Column(name = "rented_by_id", columnDefinition = "INTEGER")
    private int rentedById;
    @Column(name = "renter_id", columnDefinition = "INTEGER")
    private int renterId;
    @Column(name = "book_id", columnDefinition = "INTEGER")
    private int bookId;
    @Column(name = "date_of_rental", columnDefinition = "DATE")
    private LocalDate dateOfRental;
    @Column(name = "date_of_return", columnDefinition = "DATE")
    private LocalDate dateOfReturn;
    @Column(name = "status", columnDefinition = "VARCHAR(255)")
    private String status;

    public Rent(int rentedById, int renterId, int bookId, LocalDate dateOfRental, LocalDate dateOfReturn, String status) {
        this.rentedById = rentedById;
        this.renterId = renterId;
        this.bookId = bookId;
        this.dateOfRental = dateOfRental;
        this.dateOfReturn = dateOfReturn;
        this.status = status;
    }
}