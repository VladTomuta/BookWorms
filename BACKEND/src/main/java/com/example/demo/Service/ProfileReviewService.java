package com.example.demo.Service;

import com.example.demo.DTO.ProfileReviewDTO;
import com.example.demo.DTOMapper.ProfileReviewDTOMapper;
import com.example.demo.Entity.ProfileReview;
import com.example.demo.Exception.IncorrectIdException;
import com.example.demo.Repository.ProfileReviewRepository;
import com.example.demo.Response.RatingResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ProfileReviewService {
    @Autowired
    private ProfileReviewRepository profileReviewRepository;
    @Autowired
    private ProfileReviewDTOMapper profileReviewDTOMapper;

    public ProfileReviewDTO addProfileReview(ProfileReview profileReview){
        return profileReviewDTOMapper.apply(profileReviewRepository.saveAndFlush(profileReview));
    }

    public ProfileReviewDTO getProfileReview(int id){
        return profileReviewRepository.findById(id)
                .stream()
                .findAny()
                .map(profileReviewDTOMapper)
                .orElseThrow(IncorrectIdException::new);
    }

    public Set<ProfileReviewDTO> getAllProfileReviewsForUser(int id) {
        return profileReviewRepository.findAll()
                .stream()
                .map(profileReviewDTOMapper)
                .filter(profileReviewDTO -> profileReviewDTO.addressed_to_id() == id)
                .collect(Collectors.toSet());
    }

    public Set<ProfileReviewDTO> getAllProfileReviewsWrittenByUser(int id) {
        return profileReviewRepository.findAll()
                .stream()
                .map(profileReviewDTOMapper)
                .filter(profileReviewDTO -> profileReviewDTO.written_by_id() == id)
                .collect(Collectors.toSet());
    }

    public RatingResponse getAverageRatingForUser(int id) {
        Set<ProfileReviewDTO> profileReviewDTOs = getAllProfileReviewsForUser(id);

        int nr = 0;
        float sum = 0;

        for(ProfileReviewDTO profileReviewDTO : profileReviewDTOs) {
            nr++;
            sum += profileReviewDTO.rating();
        }

        if (nr != 0) {
            return new RatingResponse(sum/nr, nr);
        } else {
            return new RatingResponse(0, 0);
        }
    }

    public Set<ProfileReviewDTO> getAllProfileReviews() {
        return profileReviewRepository.findAll()
                .stream()
                .map(profileReviewDTOMapper)
                .collect(Collectors.toSet());
    }

    public ProfileReviewDTO updateProfileReview(int id, ProfileReview profileReview) {
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

            return profileReviewDTOMapper.apply(profileReviewRepository.saveAndFlush(actualProfileReview));
        }
        //return some exception.
        return profileReviewDTOMapper.apply(profileReview);
    }

    public ResponseEntity<String> deleteProfileReview(int id) {
        if(profileReviewRepository.findById(id).isPresent()){
            profileReviewRepository.deleteById(id);
            return new ResponseEntity<String>("Sucsessfully deleted!", HttpStatus.OK);
        }

        return new ResponseEntity<String>("Can't find specified profile review!", HttpStatus.NOT_FOUND);
    }
}