package com.example.demo.Controller;

import com.example.demo.DTO.BookReviewDTO;
import com.example.demo.Entity.BookReview;
import com.example.demo.Service.BookReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/bookReviews")
public class BookReviewController {
    @Autowired
    private BookReviewService bookReviewService;

    @PostMapping("/addBookReview")
    public BookReviewDTO addBookReview(@RequestBody BookReview bookReview){
        return bookReviewService.addBookReview(bookReview);
    }

    @GetMapping("/getBookReview/{id}")
    public BookReviewDTO getBookReview(@PathVariable int id){
        return bookReviewService.getBookReview(id);
    }

    @PutMapping("/updateBookReview/{id}")
    public BookReviewDTO updateBookReview(@PathVariable int id, @RequestBody BookReview bookReview){
        return bookReviewService.updateBookReview(id, bookReview);
    }

    @DeleteMapping("/deleteBookReview/{id}")
    public ResponseEntity<String> deleteBookReview(@PathVariable int id){
        return bookReviewService.deleteBookReview(id);
    }

    @GetMapping("/getAllBookReviews")
    public Set<BookReviewDTO> getAllBookReviews(){
        return bookReviewService.getAllBookReviews();
    }

}
