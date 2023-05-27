package com.example.demo.DTO;



import java.time.LocalDate;

public record RentDTO(
        Integer rentId,
        Integer rentedById,
        Integer renterId,
        Integer bookId,
        String dateOfRental,
        String dateOfReturn,
        String status
) {
}