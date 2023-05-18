package com.example.demo.Repository;

import com.example.demo.Entity.Book;
import com.example.demo.Entity.User;
import org.assertj.core.api.Fail;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

@DataJpaTest
class BookRepositoryTest {
    @Autowired
    private BookRepository bookRepository;

    @AfterEach
    void tearDown() {
        bookRepository.deleteAll();
    }

    @Test
    void itShouldCheckIfTitleAndAuthorExists() {
        //given
        String title = "Harap Alb";
        String author = "Ion Creanga";

        Book book = new Book(
                title,
                author,
                "genre"
        );

        bookRepository.save(book);

        //when
        Optional<Book> bookInDatabase = bookRepository.findByTitleAndAuthor(title, author);

        //then
        if(bookInDatabase.isPresent()) {
            assertEquals(bookInDatabase.get(), book);
        } else {
            Assertions.fail();
        }


    }

}