package com.example.demo.Controller;

import com.example.demo.DTO.AddBookDTO;
import com.example.demo.DTO.BookDTO;
import com.example.demo.DTO.UserDTO;
import com.example.demo.Entity.Book;
import com.example.demo.Service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/books")
@CrossOrigin("http://localhost:3000")
public class BookController {
    @Autowired
    private BookService bookService;

    // this method must be changed in order to add a book it must be assigned to a user/customer
    // in order to do that I will assume that a user will add a book, not an independent book
    @PostMapping("/{id}/addBook")
    public BookDTO addBook(@PathVariable int id, @RequestBody AddBookDTO addBookDTO){

        Book book = new Book(
                addBookDTO.title(),
                addBookDTO.author(),
                addBookDTO.genre()
        );

        return bookService.addBook(id,book);
    }

    @GetMapping("/getBook/{id}")
    public BookDTO getBook(@PathVariable int id){
        return bookService.getBook(id);
    }

    @PutMapping("/updateBook/{id}")
    public BookDTO updateBook(@PathVariable int id, @RequestBody Book book){
        return bookService.updateBook(id, book);
    }

    @DeleteMapping("/deleteBook/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable int id){
        return bookService.deleteBook(id);
    }


    @GetMapping("/getAllBooks")
    public Set<BookDTO> getAllBooks(){
        return bookService.getAllBooks();
    }

    @GetMapping("getAllOwnersOfBook/{id}")
    public Set<UserDTO> getAllOwnersOfBook(@PathVariable int id){return bookService.getAllOwnersOfBook(id);}
}
