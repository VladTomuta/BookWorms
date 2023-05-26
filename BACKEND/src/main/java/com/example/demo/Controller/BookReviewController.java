package com.example.demo.Controller;

import com.example.demo.DTO.BookReviewDTO;
import com.example.demo.DTO.ProfileReviewDTO;
import com.example.demo.Entity.BookReview;
import com.example.demo.Entity.ProfileReview;
import com.example.demo.Response.RatingResponse;
import com.example.demo.Service.BookReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Set;

@RestController
@RequestMapping("/bookReviews")
public class BookReviewController {
    @Autowired
    private BookReviewService bookReviewService;

    @PostMapping("/addBookReview")
    public BookReviewDTO addBookReview(@RequestBody BookReviewDTO bookReviewDTO){

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        BookReview bookReview = new BookReview(
                bookReviewDTO.written_by_id(),
                bookReviewDTO.description(),
                bookReviewDTO.rating(),
                bookReviewDTO.addressed_to_id(),
                LocalDate.parse(bookReviewDTO.date_of_review(), formatter)
        );

        return bookReviewService.addBookReview(bookReview);
    }

    @GetMapping("/getBookReview/{id}")
    public BookReviewDTO getBookReview(@PathVariable int id){
        return bookReviewService.getBookReview(id);
    }

    @PutMapping("/updateBookReview/{id}")
    public BookReviewDTO updateBookReview(@PathVariable int id, @RequestBody BookReviewDTO bookReviewDTO){

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        BookReview bookReview = new BookReview(
                bookReviewDTO.written_by_id(),
                bookReviewDTO.description(),
                bookReviewDTO.rating(),
                bookReviewDTO.addressed_to_id(),
                LocalDate.parse(bookReviewDTO.date_of_review(), formatter)
        );

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

    @GetMapping("/getAllBookReviewsForBook/{id}")
    public Set<BookReviewDTO> getAllBookReviewsForBook(@PathVariable int id) {return bookReviewService.getAllBookReviewsForBook(id);}

    @GetMapping("/getAllBookReviewsWrittenByUser/{id}")
    public Set<BookReviewDTO> getAllProfileReviewsWrittenByUser(@PathVariable int id) {return bookReviewService.getAllBookReviewsWrittenByUser(id);}

    @GetMapping("/getAverageRatingForBook/{id}")
    public RatingResponse getAverageRatingForUser(@PathVariable int id) {return bookReviewService.getAverageRatingForBook(id);}
}
