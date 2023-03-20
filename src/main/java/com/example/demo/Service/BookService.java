package com.example.demo.Service;

import com.example.demo.Entity.Book;
import com.example.demo.Repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    public Book addBook(Book book){
        return bookRepository.saveAndFlush(book);
    }

    public Book getBook(int id){
        return bookRepository.findById(id).get();
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book updateBook(int id, Book book) {
        if(bookRepository.findById(id).isPresent()){
            Book actualBook = bookRepository.findById(id).get();
            if(book.getAuthor() != null){
                actualBook.setAuthor(book.getAuthor());
            }
            if(book.getTitle() != null){
                actualBook.setTitle(book.getTitle());
            }
            if(book.getGenre() != null){
                actualBook.setGenre(book.getGenre());
            }

            return bookRepository.saveAndFlush(actualBook);
        }
        //return some exception.
        return book;
    }

    public ResponseEntity<String> deleteBook(int id) {
        if(bookRepository.findById(id).isPresent()){
            bookRepository.deleteById(id);
            return new ResponseEntity<String>("Sucsessfully deleted!", HttpStatus.OK);
        }

        return new ResponseEntity<String>("Can't find specified book!", HttpStatus.NOT_FOUND);
    }
}
