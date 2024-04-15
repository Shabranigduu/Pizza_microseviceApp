package com.example.feedback.service;

import com.example.feedback.dto.ReviewDto;

import java.util.List;

public interface ReviewService {

    List<ReviewDto> getReviewsByUserId(int userId);
    ReviewDto createReview(ReviewDto reviewDto);
    List<ReviewDto> getAllReviews();
    void deleteReviewsByUserId(int userId);
}
