package com.example.feedback.service.impl;

import com.example.feedback.dto.ReviewDto;
import com.example.feedback.entity.Review;
import com.example.feedback.mapper.ReviewMapper;
import com.example.feedback.repository.ReviewRepository;
import com.example.feedback.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final ReviewMapper reviewMapper;

    @Autowired
    public ReviewServiceImpl(ReviewRepository reviewRepository, ReviewMapper reviewMapper) {
        this.reviewRepository = reviewRepository;
        this.reviewMapper = reviewMapper;
    }

    @Override
    public List<ReviewDto> getReviewsByUserId(int userId) {
        List<Review> reviews = reviewRepository.findByUserId(userId);
        return reviewMapper.toReviewDtoList(reviews);
    }

    @Override
    public ReviewDto createReview(ReviewDto reviewDto) {
        Review review = reviewMapper.toReviewEntity(reviewDto);
        Review savedReview = reviewRepository.save(review);
        return reviewMapper.toReviewDto(savedReview);
    }

    @Override
    public List<ReviewDto> getAllReviews() {
        List<Review> reviews = reviewRepository.findAll();
        return reviewMapper.toReviewDtoList(reviews);
    }

    @Override
    public void deleteReviewsByUserId(int userId) {
        reviewRepository.deleteByUserId(userId);
    }
}
