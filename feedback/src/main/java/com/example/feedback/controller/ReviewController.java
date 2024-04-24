package com.example.feedback.controller;

import com.example.feedback.dto.ReviewDto;
import com.example.feedback.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/review")
public class ReviewController {

    private final ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping("/{userId}")
    public List<ReviewDto> getReviewsByUserId(@PathVariable int userId) {
        return reviewService.getReviewsByUserId(userId);
    }

    @PostMapping
    public  ReviewDto createReview(@RequestBody ReviewDto reviewDto) {
        return reviewService.createReview(reviewDto);
    }

    @GetMapping
    public List<ReviewDto> getAllReviews() {
        return reviewService.getAllReviews();
    }

    @DeleteMapping("/{userId}")
    public void deleteReviewsByUserId(@PathVariable int userId) {
        reviewService.deleteReviewsByUserId(userId);
    }
}
