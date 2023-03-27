package com.example.demo.Repository;

import com.example.demo.Entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book,Integer> {

    //In JpaRepository there is a convention that allows you to make easier queries such as findBy[EXACT_PROPERTY][And/Or][EXACT_PROPERTY]
    Optional<Book> findByTitleAndAuthor(String title, String author);
}
