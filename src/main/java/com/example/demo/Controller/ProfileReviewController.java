package com.example.demo.Controller;

import com.example.demo.Entity.ProfileReview;
import com.example.demo.Service.ProfileReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/profileReviews")
public class ProfileReviewController {
    @Autowired
    private ProfileReviewService profileReviewService;

    @PostMapping("/addProfileReview")
    public ProfileReview addProfileReview(@RequestBody ProfileReview profileReview){
        return profileReviewService.addProfileReview(profileReview);
    }

    @GetMapping("/getProfileReview/{id}")
    public ProfileReview getProfileReview(@PathVariable int id){
        return profileReviewService.getProfileReview(id);
    }

    @PutMapping("/updateProfileReview/{id}")
    public ProfileReview updateProfileReview(@PathVariable int id, @RequestBody ProfileReview profileReview){
        return profileReviewService.updateProfileReview(id, profileReview);
    }

    @DeleteMapping("/deleteProfileReview/{id}")
    public ResponseEntity<String> deleteProfileReview(@PathVariable int id){
        return profileReviewService.deleteProfileReview(id);
    }


    @GetMapping("/getAllProfileReview")
    public List<ProfileReview> getAllBooks(){
        return profileReviewService.getAllProfileReviews();
    }

}
