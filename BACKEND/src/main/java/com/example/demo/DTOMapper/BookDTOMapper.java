package com.example.demo.DTOMapper;

import com.example.demo.DTO.BookDTO;
import com.example.demo.Entity.Book;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class BookDTOMapper implements Function<Book, BookDTO>{
    @Override
    public BookDTO apply(Book book) {
        return new BookDTO(
                book.getBook_id(),
                book.getTitle(),
                book.getAuthor(),
                book.getGenre()
        );
    }
}