package com.example.demo.DTO;

public record BookDTO (
        Integer book_id,
        String title,
        String author,
        String genre
){
}