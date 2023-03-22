package com.example.demo.Service;

import com.example.demo.Entity.ProfileReview;
import com.example.demo.Repository.ProfileReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfileReviewService {
    @Autowired
    private ProfileReviewRepository profileReviewRepository;

    public ProfileReview addProfileReview(ProfileReview profileReview){
        return profileReviewRepository.saveAndFlush(profileReview);
    }

    public ProfileReview getProfileReview(int id){
        return profileReviewRepository.findById(id).get();
    }

    public List<ProfileReview> getAllProfileReviews() {
        return profileReviewRepository.findAll();
    }

    public ProfileReview updateProfileReview(int id, ProfileReview profileReview) {
        if(profileReviewRepository.findById(id).isPresent()){
            ProfileReview actualProfileReview = profileReviewRepository.findById(id).get();

            if(profileReview.getDate_of_review() != null){
                actualProfileReview.setDate_of_review(profileReview.getDate_of_review());
            }

            if(profileReview.getDescription() != null) {
                actualProfileReview.setDescription(profileReview.getDescription());
            }

            if(profileReview.getRating() != 0) {
                actualProfileReview.setRating(profileReview.getRating());
            }

            if(profileReview.getAddressed_to_id() != 0) {
                actualProfileReview.setAddressed_to_id(profileReview.getAddressed_to_id());
            }

            if(profileReview.getWritten_by_id() != 0) {
                actualProfileReview.setWritten_by_id(profileReview.getWritten_by_id());
            }

            return profileReviewRepository.saveAndFlush(actualProfileReview);
        }
        //return some exception.
        return profileReview;
    }

    public ResponseEntity<String> deleteProfileReview(int id) {
        if(profileReviewRepository.findById(id).isPresent()){
            profileReviewRepository.deleteById(id);
            return new ResponseEntity<String>("Sucsessfully deleted!", HttpStatus.OK);
        }

        return new ResponseEntity<String>("Can't find specified profile review!", HttpStatus.NOT_FOUND);
    }
}