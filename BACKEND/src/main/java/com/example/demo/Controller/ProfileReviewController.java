package com.example.demo.Controller;

import com.example.demo.DTO.ProfileReviewDTO;
import com.example.demo.Entity.ProfileReview;
import com.example.demo.Response.RatingResponse;
import com.example.demo.Service.ProfileReviewService;
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
public class ProfileReviewController {
    @Autowired
    private ProfileReviewService profileReviewService;

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

    @GetMapping("/getProfileReview/{id}")
    public ProfileReviewDTO getProfileReview(@PathVariable int id){
        return profileReviewService.getProfileReview(id);
    }

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

    @DeleteMapping("/deleteProfileReview/{id}")
    public ResponseEntity<String> deleteProfileReview(@PathVariable int id){
        return profileReviewService.deleteProfileReview(id);
    }

    @GetMapping("/getAllProfileReview")
    public Set<ProfileReviewDTO> getAllBooks(){
        return profileReviewService.getAllProfileReviews();
    }

    @GetMapping("/getAllProfileReviewsForUser/{id}")
    public Set<ProfileReviewDTO> getAllProfileReviewsForUser(@PathVariable int id) {return profileReviewService.getAllProfileReviewsForUser(id);}

    @GetMapping("/getAllProfileReviewsWrittenByUser/{id}")
    public Set<ProfileReviewDTO> getAllProfileReviewsWrittenByUser(@PathVariable int id) {return profileReviewService.getAllProfileReviewsWrittenByUser(id);}

    @GetMapping("/getAverageRatingForUser/{id}")
    public RatingResponse getAverageRatingForUser(@PathVariable int id) {return profileReviewService.getAverageRatingForUser(id);}
}
