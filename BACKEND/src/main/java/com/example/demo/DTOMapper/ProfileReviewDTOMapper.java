package com.example.demo.DTOMapper;

import com.example.demo.DTO.ProfileReviewDTO;
import com.example.demo.Entity.ProfileReview;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class ProfileReviewDTOMapper implements Function<ProfileReview, ProfileReviewDTO> {
    @Override
    public ProfileReviewDTO apply(ProfileReview profileReview) {
        return new ProfileReviewDTO(
                profileReview.getReview_id(),
                profileReview.getWritten_by_id(),
                profileReview.getDescription(),
                profileReview.getRating(),
                profileReview.getAddressed_to_id(),
                profileReview.getDate_of_review().toString()
        );
    }
}
