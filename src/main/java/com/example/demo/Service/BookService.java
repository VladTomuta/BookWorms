package com.example.demo.Service;

import com.example.demo.DTO.BookDTO;
import com.example.demo.DTOMapper.BookDTOMapper;
import com.example.demo.Entity.Book;
import com.example.demo.Entity.User;
import com.example.demo.Exception.IncorrectIdException;
import com.example.demo.Repository.BookRepository;
import com.example.demo.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookDTOMapper bookDTOMapper;

    public BookDTO addBook(int id, Book book){

        if(book.getTitle().isEmpty() || book.getAuthor().isEmpty()
                || book.getGenre().isEmpty())
            throw new IncorrectIdException();

        if(userRepository.findById(id).isPresent())
        {
            //for the actualUser I do the check on the above if
            User actualUser = userRepository.findById(id).get();

            if(bookRepository.findByTitleAndAuthor(book.getTitle(),book.getAuthor()).isPresent()){

                Book actualBook = bookRepository.findByTitleAndAuthor(book.getTitle(),book.getAuthor()).get();
                //I add to the old set of owners the new user that owns this exact book
                actualBook.getOwnersOfTheBook().add(actualUser);
                actualUser.getBooksIOwn().add(actualBook);

                return bookDTOMapper.apply(bookRepository.saveAndFlush(actualBook));
            }

            //Collections.singleton(actualUser);
            //[Returns an immutable set containing only the specified object. The returned set is serializable.]
            actualUser.getBooksIOwn().add(book);
            book.setOwnersOfTheBook(Collections.singleton(actualUser));
            return bookDTOMapper.apply(bookRepository.saveAndFlush(book));
        }
        throw new IncorrectIdException();
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
