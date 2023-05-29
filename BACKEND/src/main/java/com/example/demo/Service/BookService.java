package com.example.demo.Service;

import com.example.demo.DTO.BookDTO;
import com.example.demo.DTO.RentDTO;
import com.example.demo.DTO.UserDTO;
import com.example.demo.DTOMapper.BookDTOMapper;
import com.example.demo.DTOMapper.RentDTOMapper;
import com.example.demo.DTOMapper.UserDTOMapper;
import com.example.demo.Entity.Book;
import com.example.demo.Entity.User;
import com.example.demo.Exception.IncorrectIdException;
import com.example.demo.Repository.BookRepository;
import com.example.demo.Repository.RentRepository;
import com.example.demo.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RentRepository rentRepository;

    @Autowired
    private BookDTOMapper bookDTOMapper;
    @Autowired
    private UserDTOMapper userDTOMapper;
    @Autowired
    private RentDTOMapper rentDTOMapper;

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

    //replaced findAny with findFirst because the id is unique.
    public BookDTO getBook(int id){
        return bookRepository.findById(id)
                .stream()
                .findFirst()
                .map(bookDTOMapper)
                .orElseThrow(IncorrectIdException::new);
    }

    public Set<BookDTO> getAllBooks() {
        return bookRepository.findAll()
                .stream()
                .map(bookDTOMapper)
                .collect(Collectors.toSet());
    }

    public Set<BookDTO> getAllAvailableBooks(int id) {

        Optional<User> user = userRepository.findById(id);

        if(user.isPresent())
        {
            Set<BookDTO> ownerBooks = user.get().getBooksIOwn()
                    .stream()
                    .map(bookDTOMapper)
                    .collect(Collectors.toSet());

            Set<BookDTO> books = bookRepository.findAll()
                    .stream()
                    .map(bookDTOMapper)
                    .filter(Predicate.not(ownerBooks::contains))
                    .collect(Collectors.toSet());

            //return books;

            Set<RentDTO> allRents = rentRepository
                    .findAll()
                    .stream()
                    .map(rentDTOMapper)
                    .collect(Collectors.toSet());

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            Set<BookDTO> availableBooks = new HashSet<>();

            System.out.println(id);

            for (BookDTO book : books) {
                //System.out.println(book.book_id());

                int numberOfOwners = book.ownersOfTheBookId().size();

                System.out.println(book.book_id());
                System.out.println(numberOfOwners);

                for (RentDTO rent : allRents) {
                    //System.out.println(rent.rentId());
                    if (Objects.equals(rent.bookId(), book.book_id()) &&
                            LocalDate.parse(rent.dateOfReturn(), formatter).isAfter(LocalDate.now())) {
                        numberOfOwners--;
                    }
                }

                System.out.println(numberOfOwners);
                System.out.println();

                if (numberOfOwners > 0) {
                    availableBooks.add(book);
                }
            }

            return availableBooks;
        }
        throw new IncorrectIdException();
    }

    public Set<UserDTO> getAllOwnersOfBook(int bookId) {
        Optional<Book> book = bookRepository.findById(bookId);

        if(book.isPresent()) {
            return book.get().getOwnersOfTheBook()
                    .stream()
                    .map(userDTOMapper)
                    .collect(Collectors.toSet());
        }
        throw new IncorrectIdException();
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
        throw new IncorrectIdException();
    }

    public ResponseEntity<String> deleteBook(int id) {
        if(bookRepository.findById(id).isPresent()){
            bookRepository.deleteById(id);
            return new ResponseEntity<String>("Sucsessfully deleted!", HttpStatus.OK);
        }

        return new ResponseEntity<String>("Can't find specified book!", HttpStatus.NOT_FOUND);
    }
}
