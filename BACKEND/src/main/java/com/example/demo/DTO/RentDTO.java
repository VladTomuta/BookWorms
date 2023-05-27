package com.example.demo.DTO;



import java.time.LocalDate;

public record RentDTO(
        Integer rent_id,
        Integer rented_by_id,
        Integer renter_id,
        Integer book_id,
        LocalDate date_of_rental,
        LocalDate date_of_return,
        String status
) {
}