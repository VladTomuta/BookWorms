package com.example.demo.Entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "rents")
public class Rents {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int rent_id;
    @Column(name = "renter_by_id", columnDefinition = "INTEGER")
    private int renter_by_id;
    @Column(name = "renter_id", columnDefinition = "INTEGER")
    private int renter_id;
    @Column(name = "date_of_rental", columnDefinition = "DATE")
    private LocalDate date_of_rental;
    @Column(name = "date_of_return", columnDefinition = "DATE")
    private LocalDate date_of_return;
}