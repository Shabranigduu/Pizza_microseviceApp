package com.example.feedback.service.impl;

import com.example.feedback.dto.ReviewDto;
import com.example.feedback.entity.Review;
import com.example.feedback.exception.ReviewNotFoundException;
import com.example.feedback.exception.ReviewServiceException;
import com.example.feedback.mapper.ReviewMapper;
import com.example.feedback.repository.ReviewRepository;
import com.example.feedback.service.ReviewService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final ReviewMapper reviewMapper;

    public ReviewServiceImpl(ReviewRepository reviewRepository, ReviewMapper reviewMapper) {
        this.reviewRepository = reviewRepository;
        this.reviewMapper = reviewMapper;
    }

    @Override
    public List<ReviewDto> getReviewsByUserId(int userId) {
        try {
            List<Review> reviews = reviewRepository.findByUserId(userId);
            return reviewMapper.toReviewDtoList(reviews);
        } catch (DataAccessException ex) {
            throw new ReviewServiceException("Failed to retrieve reviews for user with ID: " + userId, ex);
        }
    }

    @Override
    public ReviewDto createReview(ReviewDto reviewDto) {
        try {
            Review review = reviewMapper.toReviewEntity(reviewDto);
            Review savedReview = reviewRepository.save(review);
            return reviewMapper.toReviewDto(savedReview);
        } catch (DataAccessException ex) {
            throw new ReviewServiceException("Failed to create review", ex);
        }
    }

    @Override
    public List<ReviewDto> getAllReviews() {
        try {
            List<Review> reviews = reviewRepository.findAll();
            return reviewMapper.toReviewDtoList(reviews);
        } catch (DataAccessException ex) {
            throw new ReviewServiceException("Failed to retrieve all reviews", ex);
        }
    }

    @Override
    @Transactional
    public void deleteReviewsByUserId(int userId) {
        try {
            reviewRepository.deleteByUserId(userId);
        } catch (EmptyResultDataAccessException ex) {
            throw new ReviewNotFoundException("Reviews not found for user with ID: " + userId);
        } catch (DataAccessException ex) {
            throw new ReviewServiceException("Failed to delete reviews for user with ID: " + userId, ex);
        }
    }
}
