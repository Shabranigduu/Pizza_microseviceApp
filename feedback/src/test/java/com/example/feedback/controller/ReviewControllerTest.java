package com.example.feedback.controller;

import com.example.feedback.dto.ReviewDto;
import com.example.feedback.service.ReviewService;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ReviewControllerTest {

    @Mock
    private ReviewService reviewService;

    @InjectMocks
    private ReviewController reviewController;

    @Test
    public void testGetReviewsByUserId() {
        // Define test data
        int userId = 1;
        List<ReviewDto> expectedReviews = Arrays.asList(new ReviewDto(), new ReviewDto());

        // Mock behavior
        when(reviewService.getReviewsByUserId(userId)).thenReturn(expectedReviews);

        // Perform the test
        List<ReviewDto> actualReviews = reviewController.getReviewsByUserId(userId);

        // Verify
        assertEquals(expectedReviews.size(), actualReviews.size());
        // Add more assertions as needed
    }

    @Test
    public void testCreateReview() {
        // Define test data
        ReviewDto reviewDto = new ReviewDto();
        ReviewDto createdReview = new ReviewDto(); // Assuming this is the response from service

        // Mock behavior
        when(reviewService.createReview(reviewDto)).thenReturn(createdReview);

        // Perform the test
        ReviewDto actualReview = reviewController.createReview(reviewDto);

        // Verify
        assertNotNull(actualReview);
        // Add more assertions as needed
    }

    @Test
    public void testGetAllReviews() {
        // Define test data
        List<ReviewDto> expectedReviews = Arrays.asList(new ReviewDto(), new ReviewDto());

        // Mock behavior
        when(reviewService.getAllReviews()).thenReturn(expectedReviews);

        // Perform the test
        List<ReviewDto> actualReviews = reviewController.getAllReviews();

        // Verify
        assertEquals(expectedReviews.size(), actualReviews.size());
        // Add more assertions as needed
    }

    @Test
    public void testDeleteReviewsByUserId() {
        // Define test data
        int userId = 1;

        // Perform the test
        reviewController.deleteReviewsByUserId(userId);

        // Verify
        verify(reviewService, times(1)).deleteReviewsByUserId(userId);
        // Add more assertions as needed
    }

    @Test
    public void testGetReviewsByInvalidUserId() {
        // Define test data
        int invalidUserId = -1;

        // Mock behavior
        when(reviewService.getReviewsByUserId(invalidUserId)).thenThrow(new IllegalArgumentException());

        // Perform the test and verify the exception
        assertThrows(IllegalArgumentException.class, () -> reviewController.getReviewsByUserId(invalidUserId));
    }

    @Test
    public void testCreateReviewWithNullInput() {
        // Perform the test with null input
        assertThrows(IllegalArgumentException.class, () -> reviewController.createReview(null));
    }

    @Test
    public void testGetAllReviewsWithNoData() {
        // Define test data
        List<ReviewDto> emptyList = Collections.emptyList();

        // Mock behavior
        when(reviewService.getAllReviews()).thenReturn(emptyList);

        // Perform the test
        List<ReviewDto> actualReviews = reviewController.getAllReviews();

        // Verify
        assertNotNull(actualReviews);
        assertTrue(actualReviews.isEmpty());
    }

    @Test
    public void testDeleteReviewsByNonExistingUserId() {
        // Define test data
        int nonExistingUserId = 999;

        // Mock behavior for non-existing user id
        doThrow(new EntityNotFoundException()).when(reviewService).deleteReviewsByUserId(nonExistingUserId);

        // Perform the test and verify the exception
        assertThrows(EntityNotFoundException.class, () -> reviewController.deleteReviewsByUserId(nonExistingUserId));
    }
}



