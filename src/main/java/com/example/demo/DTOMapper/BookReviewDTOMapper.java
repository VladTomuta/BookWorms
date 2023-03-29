package com.example.demo.DTOMapper;

import com.example.demo.DTO.BookReviewDTO;
import com.example.demo.Entity.BookReview;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class BookReviewDTOMapper implements Function<BookReview, BookReviewDTO> {
    @Override
    public BookReviewDTO apply(BookReview bookReview) {
        return new BookReviewDTO(
                bookReview.getReview_id(),
                bookReview.getWritten_by_id(),
                bookReview.getDescription(),
                bookReview.getRating(),
                bookReview.getAddressed_to_id(),
                bookReview.getDate_of_review()
        );
    }
}
