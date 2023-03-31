package com.example.demo.Controller;

import com.example.demo.DTO.ProfileReviewDTO;
import com.example.demo.Entity.ProfileReview;
import com.example.demo.Service.ProfileReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/profileReviews")
public class ProfileReviewController {
    @Autowired
    private ProfileReviewService profileReviewService;

    @PostMapping("/addProfileReview")
    public ProfileReviewDTO addProfileReview(@RequestBody ProfileReview profileReview){
        return profileReviewService.addProfileReview(profileReview);
    }

    @GetMapping("/getProfileReview/{id}")
    public ProfileReviewDTO getProfileReview(@PathVariable int id){
        return profileReviewService.getProfileReview(id);
    }

    @PutMapping("/updateProfileReview/{id}")
    public ProfileReviewDTO updateProfileReview(@PathVariable int id, @RequestBody ProfileReview profileReview){
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

}
