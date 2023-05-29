package com.example.demo.DTO;

import com.example.demo.Entity.User;

import java.util.HashMap;
import java.util.Set;

public record BookDTO (
        Integer book_id,
        String title,
        String author,
        String genre,
        Set<Integer> ownersOfTheBookId
){
}