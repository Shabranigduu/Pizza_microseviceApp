package com.example.feedback.mapper;


import com.example.feedback.dto.ReviewDto;
import com.example.feedback.entity.Review;
import com.example.feedback.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ReviewMapper {

    private final ReviewService reviewService;

    @Autowired
    public ReviewMapper(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    public ReviewDto toReviewDto(Review review) {
        return new ReviewDto(review.getId(), review.getTitle(), review.getOrderId(), review.isGrade(), review.getDescription());
    }

    public Review toReviewEntity(ReviewDto reviewDto) {
        return new Review(reviewDto.getId(), reviewDto.getTitle(), reviewDto.getOrderId(), reviewDto.isGrade(), reviewDto.getDescription()
        );
    }

    public List<ReviewDto> toReviewDtoList(List<Review> reviews) {
        return reviews.stream()
                .map(this::toReviewDto)
                .collect(Collectors.toList());
    }
}
