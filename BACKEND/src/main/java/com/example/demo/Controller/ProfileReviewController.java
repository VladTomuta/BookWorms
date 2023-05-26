package com.example.demo.Controller;

import com.example.demo.DTO.ProfileReviewDTO;
import com.example.demo.Entity.ProfileReview;
import com.example.demo.Response.RatingResponse;
import com.example.demo.Service.ProfileReviewService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.JpaSort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Set;

@RestController
@RequestMapping("/profileReviews")
@CrossOrigin("http://localhost:3000")
@Tag(name = "Profile Review")
public class ProfileReviewController {
    @Autowired
    private ProfileReviewService profileReviewService;

    @Operation(summary = "Add a profile review")
    @PostMapping("/addProfileReview")
    public ProfileReviewDTO addProfileReview(@RequestBody ProfileReviewDTO profileReviewDTO){

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        ProfileReview profileReview = new ProfileReview(
                profileReviewDTO.written_by_id(),
                profileReviewDTO.description(),
                profileReviewDTO.rating(),
                profileReviewDTO.addressed_to_id(),
                LocalDate.parse(profileReviewDTO.date_of_review(), formatter)
        );

        return profileReviewService.addProfileReview(profileReview);
    }

    @Operation(summary = "Get a profile review given by id")
    @GetMapping("/getProfileReview/{id}")
    public ProfileReviewDTO getProfileReview(@PathVariable int id){
        return profileReviewService.getProfileReview(id);
    }

    @Operation(summary = "Update a profile review given by id")
    @PutMapping("/updateProfileReview/{id}")
    public ProfileReviewDTO updateProfileReview(@PathVariable int id, @RequestBody ProfileReviewDTO profileReviewDTO){

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        ProfileReview profileReview = new ProfileReview(
                profileReviewDTO.written_by_id(),
                profileReviewDTO.description(),
                profileReviewDTO.rating(),
                profileReviewDTO.addressed_to_id(),
                LocalDate.parse(profileReviewDTO.date_of_review(), formatter)
        );

        return profileReviewService.updateProfileReview(id, profileReview);
    }

    @Operation(summary = "Delete a profile review given by id")
    @DeleteMapping("/deleteProfileReview/{id}")
    public ResponseEntity<String> deleteProfileReview(@PathVariable int id){
        return profileReviewService.deleteProfileReview(id);
    }

    @Operation(summary = "Get all profile reviews")
    @GetMapping("/getAllProfileReview")
    public Set<ProfileReviewDTO> getAllBooks(){
        return profileReviewService.getAllProfileReviews();
    }

    @Operation(summary = "Get all profile reviews addressed to a user given by id")
    @GetMapping("/getAllProfileReviewsForUser/{id}")
    public Set<ProfileReviewDTO> getAllProfileReviewsForUser(@PathVariable int id) {return profileReviewService.getAllProfileReviewsForUser(id);}

    @Operation(summary = "Get all profile reviews written by a user given by id")
    @GetMapping("/getAllProfileReviewsWrittenByUser/{id}")
    public Set<ProfileReviewDTO> getAllProfileReviewsWrittenByUser(@PathVariable int id) {return profileReviewService.getAllProfileReviewsWrittenByUser(id);}

    @Operation(summary = "Get the average rating and number of reviews for a user given by id")
    @GetMapping("/getAverageRatingForUser/{id}")
    public RatingResponse getAverageRatingForUser(@PathVariable int id) {return profileReviewService.getAverageRatingForUser(id);}
}
