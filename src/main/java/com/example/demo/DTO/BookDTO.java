package com.example.demo.DTO;

public record BookDTO (
        Integer book_id,
        String title,
        String author,
        String genre,
        Integer Rented_by_id,
        Integer owner_id
){
}