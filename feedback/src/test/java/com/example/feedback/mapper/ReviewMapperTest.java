package com.example.feedback.mapper;

import com.example.feedback.dto.ReviewDto;
import com.example.feedback.entity.Review;
import com.example.feedback.service.ReviewService;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.*;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(MockitoExtension.class)
public class ReviewMapperTest {

    @Mock
    private ReviewService reviewService;

    @InjectMocks
    private ReviewMapper reviewMapper;

    @Test
    public void testToReviewDto() {
        Review review = new Review(1, "Title", 123, 115, true, "Description");
        ReviewDto reviewDto = reviewMapper.toReviewDto(review);
        assertEquals(review.getId(), reviewDto.getId());
        assertEquals(review.getTitle(), reviewDto.getTitle());
        assertEquals(review.getOrderId(), reviewDto.getOrderId());
        assertEquals(review.getUserId(), reviewDto.getUserId());
        assertEquals(review.isGrade(), reviewDto.isGrade());
        assertEquals(review.getDescription(), reviewDto.getDescription());
    }

    @Test
    public void testToReviewEntity() {
        ReviewDto reviewDto = new ReviewDto(1, "Title", 123, 124, true, "Description");
        Review review = reviewMapper.toReviewEntity(reviewDto);
        assertEquals(reviewDto.getId(), review.getId());
        assertEquals(reviewDto.getTitle(), review.getTitle());
        assertEquals(reviewDto.getUserId(), review.getUserId());
        assertEquals(reviewDto.getOrderId(), review.getOrderId());
        assertEquals(reviewDto.isGrade(), review.isGrade());
        assertEquals(reviewDto.getDescription(), review.getDescription());
    }

    @Test
    public void testToReviewDtoList() {
        List<Review> reviews = Arrays.asList(
                new Review(1, "Title1", 123, 124, true, "Description1"),
                new Review(2, "Title2", 456, 446, false, "Description2")
        );

        List<ReviewDto> reviewDtos = reviewMapper.toReviewDtoList(reviews);
        assertEquals(reviews.size(), reviewDtos.size());
        for (int i = 0; i < reviews.size(); i++) {
            Review review = reviews.get(i);
            ReviewDto reviewDto = reviewDtos.get(i);
            assertEquals(review.getId(), reviewDto.getId());
            assertEquals(review.getTitle(), reviewDto.getTitle());
            assertEquals(review.getOrderId(), reviewDto.getOrderId());
            assertEquals(review.getUserId(), reviewDto.getUserId());
            assertEquals(review.isGrade(), reviewDto.isGrade());
            assertEquals(review.getDescription(), reviewDto.getDescription());
        }
    }
}
