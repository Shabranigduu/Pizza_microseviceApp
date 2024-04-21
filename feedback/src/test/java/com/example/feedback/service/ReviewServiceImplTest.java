package com.example.feedback.service;

import com.example.feedback.dto.ReviewDto;
import com.example.feedback.entity.Review;
import com.example.feedback.mapper.ReviewMapper;
import com.example.feedback.repository.ReviewRepository;
import com.example.feedback.service.impl.ReviewServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
public class ReviewServiceImplTest {

    @Mock
    private ReviewRepository reviewRepository;

    @Mock
    private ReviewMapper reviewMapper;

    @InjectMocks
    private ReviewServiceImpl reviewService;

    @Test
    public void testCreateReview() {
        ReviewDto reviewDto = new ReviewDto(/* provide necessary data */);
        Review review = new Review(/* provide necessary data */);
        when(reviewMapper.toReviewEntity(reviewDto)).thenReturn(review);
        when(reviewRepository.save(review)).thenReturn(review);
        ReviewDto expectedReviewDto = new ReviewDto(/* provide mock data for created review */);
        when(reviewMapper.toReviewDto(review)).thenReturn(expectedReviewDto);
        ReviewDto createdReviewDto = reviewService.createReview(reviewDto);
        assertEquals(expectedReviewDto, createdReviewDto);
        verify(reviewMapper, times(1)).toReviewEntity(reviewDto);
        verify(reviewRepository, times(1)).save(review);
        verify(reviewMapper, times(1)).toReviewDto(review);
    }

    @Test
    public void testGetAllReviews() {
        Review review1 = new Review(/* provide necessary data */);
        Review review2 = new Review(/* provide necessary data */);
        List<Review> reviewList = Arrays.asList(review1, review2);
        when(reviewRepository.findAll()).thenReturn(reviewList);
        List<ReviewDto> expectedReviewDtoList = new ArrayList<>();
        when(reviewMapper.toReviewDtoList(reviewList)).thenReturn(expectedReviewDtoList);
        List<ReviewDto> resultReviewDtoList = reviewService.getAllReviews();
        assertEquals(expectedReviewDtoList, resultReviewDtoList);
        verify(reviewRepository, times(1)).findAll();
        verify(reviewMapper, times(1)).toReviewDtoList(reviewList);
    }

    @Test
    public void testGetReviewsByOrderId() {
        int orderId = 123;
        Review review1 = new Review(/* provide necessary data */);
        Review review2 = new Review(/* provide necessary data */);
        List<Review> reviewList = Arrays.asList(review1, review2);
        when(reviewRepository.findByOrderId(orderId)).thenReturn(reviewList);
        List<ReviewDto> expectedReviewDtoList = new ArrayList<>();
        when(reviewMapper.toReviewDtoList(reviewList)).thenReturn(expectedReviewDtoList);
        List<ReviewDto> resultReviewDtoList = reviewService.getReviewsByOrderId(orderId);
        assertEquals(expectedReviewDtoList, resultReviewDtoList);
        verify(reviewRepository, times(1)).findByOrderId(orderId);
        verify(reviewMapper, times(1)).toReviewDtoList(reviewList);
    }

    @Test
    public void testDeleteByOrderId() {
        int orderId = 123;
        reviewService.deleteByOrderId(orderId);
        verify(reviewRepository, times(1)).deleteByOrderId(orderId);
    }
}

