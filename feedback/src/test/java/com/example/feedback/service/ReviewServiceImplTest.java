package com.example.feedback.service;

import com.example.feedback.dto.ReviewDto;
import com.example.feedback.entity.Review;
import com.example.feedback.exception.ReviewNotFoundException;
import com.example.feedback.exception.ReviewServiceException;
import com.example.feedback.mapper.ReviewMapper;
import com.example.feedback.repository.ReviewRepository;
import com.example.feedback.service.impl.ReviewServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ReviewServiceImplTest {

    @Mock
    private ReviewRepository reviewRepository;

    @Mock
    private ReviewMapper reviewMapper;

    @InjectMocks
    private ReviewServiceImpl reviewService;

    @Test
    void getReviewsByUserId_Success() {
        int userId = 1;
        List<Review> reviews = Collections.singletonList(new Review());
        when(reviewRepository.findByUserId(userId)).thenReturn(reviews);
        List<ReviewDto> result = reviewService.getReviewsByUserId(userId);
        assertFalse(result.isEmpty());
        assertEquals(reviews.size(), result.size());
    }

    @Test
    void createReview_Success() {
        ReviewDto reviewDto = new ReviewDto();
        Review review = new Review();
        when(reviewMapper.toReviewEntity(reviewDto)).thenReturn(review);
        when(reviewRepository.save(review)).thenReturn(review);
        when(reviewMapper.toReviewDto(review)).thenReturn(reviewDto);
        ReviewDto result = reviewService.createReview(reviewDto);
        assertNotNull(result);
    }

    @Test
    void getReviewsByUserId_Exception() {
        int userId = 1;
        when(reviewRepository.findByUserId(userId)).thenThrow(DataAccessException.class);
        assertThrows(ReviewServiceException.class, () -> reviewService.getReviewsByUserId(userId));
    }

    @Test
    void deleteReviewsByUserId_ReviewsNotFound() {
        int userId = 1;
        doThrow(EmptyResultDataAccessException.class).when(reviewRepository).deleteByUserId(userId);
        assertThrows(ReviewNotFoundException.class, () -> reviewService.deleteReviewsByUserId(userId));
    }

    @Test
    void getAllReviews_Success() {
        List<Review> reviews = Collections.singletonList(new Review());
        when(reviewRepository.findAll()).thenReturn(reviews);
        when(reviewMapper.toReviewDtoList(reviews)).thenReturn(Collections.singletonList(new ReviewDto()));
        List<ReviewDto> result = reviewService.getAllReviews();
        assertFalse(result.isEmpty());
        assertEquals(reviews.size(), result.size());
    }

    @Test
    void deleteReviewsByUserId_Exception() {
        int userId = 1;
        doThrow(DataAccessException.class).when(reviewRepository).deleteByUserId(userId);
        assertThrows(ReviewServiceException.class, () -> reviewService.deleteReviewsByUserId(userId));
    }

}


