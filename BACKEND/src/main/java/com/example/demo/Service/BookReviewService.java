package com.example.demo.Service;

import com.example.demo.DTO.BookReviewDTO;
import com.example.demo.DTO.ProfileReviewDTO;
import com.example.demo.DTOMapper.BookReviewDTOMapper;
import com.example.demo.Entity.BookReview;
import com.example.demo.Exception.IncorrectIdException;
import com.example.demo.Repository.BookReviewRepository;
import com.example.demo.Response.RatingResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BookReviewService {
    @Autowired
    private BookReviewRepository bookReviewRepository;
    @Autowired
    private BookReviewDTOMapper bookReviewDTOMapper;

    public BookReviewDTO addBookReview(BookReview bookReview){
        return bookReviewDTOMapper.apply(bookReviewRepository.saveAndFlush(bookReview));
    }

    public BookReviewDTO getBookReview(int id){
        return bookReviewRepository.findById(id)
                .stream()
                .findAny()
                .map(bookReviewDTOMapper)
                .orElseThrow(IncorrectIdException::new);
    }

    public Set<BookReviewDTO> getAllBookReviewsForBook(int id) {
        return bookReviewRepository.findAll()
                .stream()
                .map(bookReviewDTOMapper)
                .filter(bookReviewDTO -> bookReviewDTO.addressed_to_id() == id)
                .collect(Collectors.toSet());
    }

    public Set<BookReviewDTO> getAllBookReviewsWrittenByUser(int id) {
        return bookReviewRepository.findAll()
                .stream()
                .map(bookReviewDTOMapper)
                .filter(bookReviewDTO -> bookReviewDTO.written_by_id() == id)
                .collect(Collectors.toSet());
    }

    public RatingResponse getAverageRatingForBook(int id) {
        Set<BookReviewDTO> bookReviewDTOs = getAllBookReviewsForBook(id);

        int nr = 0;
        float sum = 0;

        for(BookReviewDTO bookReviewDTO : bookReviewDTOs) {
            nr++;
            sum += bookReviewDTO.rating();
        }

        if (nr != 0) {
            return new RatingResponse(sum/nr, nr);
        } else {
            return new RatingResponse(0, 0);
        }
    }

    public Set<BookReviewDTO> getAllBookReviews() {
        return bookReviewRepository.findAll()
                .stream()
                .map(bookReviewDTOMapper)
                .collect(Collectors.toSet());
    }

    public BookReviewDTO updateBookReview(int id, BookReview bookReview) {
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

            return bookReviewDTOMapper.apply(bookReviewRepository.saveAndFlush(actualBookReview));
        }
        //return some exception.
        return bookReviewDTOMapper.apply(bookReview);
    }

    public ResponseEntity<String> deleteBookReview(int id) {
        if(bookReviewRepository.findById(id).isPresent()){
            bookReviewRepository.deleteById(id);
            return new ResponseEntity<String>("Sucsessfully deleted!", HttpStatus.OK);
        }

        return new ResponseEntity<String>("Can't find specified book review!", HttpStatus.NOT_FOUND);
    }
}