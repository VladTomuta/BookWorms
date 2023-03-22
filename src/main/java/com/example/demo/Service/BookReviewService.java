package com.example.demo.Service;

import com.example.demo.Entity.BookReview;
import com.example.demo.Repository.BookReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookReviewService {
    @Autowired
    private BookReviewRepository bookReviewRepository;

    public BookReview addBookReview(BookReview bookReview){
        return bookReviewRepository.saveAndFlush(bookReview);
    }

    public BookReview getBookReview(int id){
        return bookReviewRepository.findById(id).get();
    }

    public List<BookReview> getAllBookReviews() {
        return bookReviewRepository.findAll();
    }

    public BookReview updateBookReview(int id, BookReview bookReview) {
        if(bookReviewRepository.findById(id).isPresent()){
            BookReview actualBookReview = bookReviewRepository.findById(id).get();

            if(bookReview.getDate_of_review() != null){
                actualBookReview.setDate_of_review(bookReview.getDate_of_review());
            }

            if(bookReview.getDescription() != null) {
                actualBookReview.setDescription(bookReview.getDescription());
            }

            if(bookReview.getRating() != 0) {
                actualBookReview.setRating(bookReview.getRating());
            }

            if(bookReview.getAddressed_to_id() != 0) {
                actualBookReview.setAddressed_to_id(bookReview.getAddressed_to_id());
            }

            if(bookReview.getWritten_by_id() != 0) {
                actualBookReview.setWritten_by_id(bookReview.getWritten_by_id());
            }

            return bookReviewRepository.saveAndFlush(actualBookReview);
        }
        //return some exception.
        return bookReview;
    }

    public ResponseEntity<String> deleteBookReview(int id) {
        if(bookReviewRepository.findById(id).isPresent()){
            bookReviewRepository.deleteById(id);
            return new ResponseEntity<String>("Sucsessfully deleted!", HttpStatus.OK);
        }

        return new ResponseEntity<String>("Can't find specified book review!", HttpStatus.NOT_FOUND);
    }
}