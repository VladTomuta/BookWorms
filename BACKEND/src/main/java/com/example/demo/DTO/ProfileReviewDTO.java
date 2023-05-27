package com.example.demo.DTO;

import java.time.LocalDate;

public record ProfileReviewDTO(
        Integer review_id,
        Integer written_by_id,
        String description,
        Float rating,
        Integer addressed_to_id,
        String date_of_review
) {
}