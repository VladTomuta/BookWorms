package com.example.demo.Repository;

import com.example.demo.Entity.BookReview;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookReviewRepository extends JpaRepository<BookReview,Integer> {
}
