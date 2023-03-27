package com.example.demo.Service;

import com.example.demo.DTO.BookDTO;
import com.example.demo.DTOMapper.BookDTOMapper;
import com.example.demo.Entity.Book;
import com.example.demo.Exception.IncorrectIdException;
import com.example.demo.Repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {


    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private BookDTOMapper bookDTOMapper;

    public BookDTO addBook(Book book){
        return bookDTOMapper.apply(bookRepository.saveAndFlush(book));
    }

    public BookDTO getBook(int id){
        return bookRepository.findById(id)
                .stream()
                .findAny()
                .map(bookDTOMapper)
                .orElseThrow(IncorrectIdException::new);
    }

    public List<BookDTO> getAllBooks() {
        return bookRepository.findAll()
                .stream()
                .map(bookDTOMapper)
                .collect(Collectors.toList());
    }

    public BookDTO updateBook(int id, Book book) {
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

            return bookDTOMapper.apply(bookRepository.saveAndFlush(book));
        }
        //return some exception.
        return bookDTOMapper.apply(book);
    }

    public ResponseEntity<String> deleteBook(int id) {
        if(bookRepository.findById(id).isPresent()){
            bookRepository.deleteById(id);
            return new ResponseEntity<String>("Sucsessfully deleted!", HttpStatus.OK);
        }

        return new ResponseEntity<String>("Can't find specified book!", HttpStatus.NOT_FOUND);
    }
}
