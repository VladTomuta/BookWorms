package com.example.demo.Controller;

import com.example.demo.DTO.BookReviewDTO;
import com.example.demo.DTO.ProfileReviewDTO;
import com.example.demo.Entity.BookReview;
import com.example.demo.Entity.ProfileReview;
import com.example.demo.Response.RatingResponse;
import com.example.demo.Service.BookReviewService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Set;

@RestController
@RequestMapping("/bookReviews")
@CrossOrigin("http://localhost:3000")
@Tag(name = "Book Review")
public class BookReviewController {
    @Autowired
    private BookReviewService bookReviewService;

    @Operation(summary = "Add a book review")
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

    @Operation(summary = "Get a book review given by id")
    @GetMapping("/getBookReview/{id}")
    public BookReviewDTO getBookReview(@PathVariable int id){
        return bookReviewService.getBookReview(id);
    }

    @Operation(summary = "Update a book review given by id")
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

    @Operation(summary = "Delete a book review given by id")
    @DeleteMapping("/deleteBookReview/{id}")
    public ResponseEntity<String> deleteBookReview(@PathVariable int id){
        return bookReviewService.deleteBookReview(id);
    }

    @Operation(summary = "Get all book reviews")
    @GetMapping("/getAllBookReviews")
    public Set<BookReviewDTO> getAllBookReviews(){
        return bookReviewService.getAllBookReviews();
    }

    @Operation(summary = "Get all book reviews addressed to a book given by id")
    @GetMapping("/getAllBookReviewsForBook/{id}")
    public Set<BookReviewDTO> getAllBookReviewsForBook(@PathVariable int id) {return bookReviewService.getAllBookReviewsForBook(id);}

    @Operation(summary = "Get all book reviews written by a user given by id")
    @GetMapping("/getAllBookReviewsWrittenByUser/{id}")
    public Set<BookReviewDTO> getAllProfileReviewsWrittenByUser(@PathVariable int id) {return bookReviewService.getAllBookReviewsWrittenByUser(id);}

    @Operation(summary = "Get the average rating and number of reviews for a user given by id")
    @GetMapping("/getAverageRatingForBook/{id}")
    public RatingResponse getAverageRatingForUser(@PathVariable int id) {return bookReviewService.getAverageRatingForBook(id);}
}
