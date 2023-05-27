package com.example.demo.Controller;

import com.example.demo.DTO.AddBookDTO;
import com.example.demo.DTO.BookDTO;
import com.example.demo.DTO.UserDTO;
import com.example.demo.Entity.Book;
import com.example.demo.Service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/books")
@CrossOrigin("http://localhost:3000")
@Tag(name = "Book")
public class BookController {
    @Autowired
    private BookService bookService;

    // this method must be changed in order to add a book it must be assigned to a user/customer
    // in order to do that I will assume that a user will add a book, not an independent book
    @Operation(summary = "Add a book to a user given by id")
    @PostMapping("/addBook/{id}")
    public BookDTO addBook(@PathVariable int id, @RequestBody AddBookDTO addBookDTO){

        Book book = new Book(
                addBookDTO.title(),
                addBookDTO.author(),
                addBookDTO.genre()
        );

        return bookService.addBook(id,book);
    }

    @Operation(summary = "Get a book given by id")
    @GetMapping("/getBook/{id}")
    public BookDTO getBook(@PathVariable int id){
        return bookService.getBook(id);
    }

    @Operation(summary = "Update a book given by id")
    @PutMapping("/updateBook/{id}")
    public BookDTO updateBook(@PathVariable int id, @RequestBody BookDTO bookDTO){

        Book book = new Book(
                bookDTO.title(),
                bookDTO.author(),
                bookDTO.genre()
        );

        return bookService.updateBook(id, book);
    }

    @Operation(summary = "Delete a book given by id")
    @DeleteMapping("/deleteBook/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable int id){
        return bookService.deleteBook(id);
    }

    @Operation(summary = "Get all books")
    @GetMapping("/getAllBooks")
    public Set<BookDTO> getAllBooks(){
        return bookService.getAllBooks();
    }

    @Operation(summary = "Get all owners of a book given by id")
    @GetMapping("getAllOwnersOfBook/{id}")
    public Set<UserDTO> getAllOwnersOfBook(@PathVariable int id){return bookService.getAllOwnersOfBook(id);}
}
