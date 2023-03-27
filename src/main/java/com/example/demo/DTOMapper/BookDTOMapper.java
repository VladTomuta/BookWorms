package com.example.demo.DTOMapper;

import com.example.demo.DTO.BookDTO;
import com.example.demo.DTO.UserDTO;
import com.example.demo.Entity.Book;
import com.example.demo.Entity.User;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class BookDTOMapper implements Function<Book, BookDTO>{
    @Override
    public BookDTO apply(Book book) {
        return new BookDTO(
                book.getBook_id(),
                book.getAuthor(),
                book.getTitle(),
                book.getGenre(),
                book.getRented_by_id(),
                book.getOwner_id()
        );
    }
}