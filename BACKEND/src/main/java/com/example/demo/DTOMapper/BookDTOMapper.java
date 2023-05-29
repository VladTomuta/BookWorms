package com.example.demo.DTOMapper;

import com.example.demo.DTO.BookDTO;
import com.example.demo.Entity.Book;
import com.example.demo.Entity.User;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;

@Service
public class BookDTOMapper implements Function<Book, BookDTO>{



    @Override
    public BookDTO apply(Book book) {

        Set<User> ownersOfTheBook = book.getOwnersOfTheBook();

        Set<Integer> ownersId = new HashSet<>();

        for(User user : ownersOfTheBook) {
            ownersId.add(user.getUser_id());
        }

        return new BookDTO(
                book.getBook_id(),
                book.getTitle(),
                book.getAuthor(),
                book.getGenre(),
                ownersId
        );
    }
}