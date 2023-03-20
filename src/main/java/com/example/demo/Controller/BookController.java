package com.example.demo.Controller;

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
    public Book addBook(@RequestBody Book book){
        return bookService.addBook(book);
    }

    @GetMapping("/getBook/{id}")
    public Book getBook(@PathVariable int id){
        return bookService.getBook(id);
    }

    @PutMapping("/updateBook/{id}")
    public Book updateBook(@PathVariable int id, @RequestBody Book book){
        return bookService.updateBook(id, book);
    }

    @DeleteMapping("/deleteBook/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable int id){
        return bookService.deleteBook(id);
    }


    @GetMapping("/getAllBooks")
    public List<Book> getAllBooks(){
        return bookService.getAllBooks();
    }

}
