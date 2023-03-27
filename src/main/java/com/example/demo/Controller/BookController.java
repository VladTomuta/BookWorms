package com.example.demo.Controller;

import com.example.demo.DTO.BookDTO;
import com.example.demo.Entity.Book;
import com.example.demo.Service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {
    @Autowired
    private BookService bookService;

    @PostMapping("/addBook")
    public BookDTO addBook(@RequestBody Book book){
        return bookService.addBook(book);
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
    public List<BookDTO> getAllBooks(){
        return bookService.getAllBooks();
    }

}
