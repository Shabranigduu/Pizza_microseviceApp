package com.example.feedback.mapper;

import com.example.feedback.dto.ReviewDto;
import com.example.feedback.entity.Review;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ReviewMapperTest {

    @Mock
    private Review reviewMock;

    @InjectMocks
    private ReviewMapper reviewMapper;

    @Test
    public void testToReviewDto() {
        when(reviewMock.getId()).thenReturn(1);
        when(reviewMock.getTitle()).thenReturn("Sample Title");
        when(reviewMock.getOrderId()).thenReturn(123);
        when(reviewMock.isGrade()).thenReturn(true);
        when(reviewMock.getDescription()).thenReturn("Sample Description");
        ReviewDto reviewDto = reviewMapper.toReviewDto(reviewMock);
        assertEquals(1, reviewDto.getId());
        assertEquals("Sample Title", reviewDto.getTitle());
        assertEquals(123, reviewDto.getOrderId());
        assertEquals(true, reviewDto.isGrade());
        assertEquals("Sample Description", reviewDto.getDescription());
    }

    @Test
    public void testToReviewEntity() {
        ReviewDto reviewDto = new ReviewDto(1, "Sample Title", 123, true, "Sample Description");
        Review reviewEntity = reviewMapper.toReviewEntity(reviewDto);
        assertEquals(1L, reviewEntity.getId());
        assertEquals("Sample Title", reviewEntity.getTitle());
        assertEquals(123, reviewEntity.getOrderId());
        assertEquals(true, reviewEntity.isGrade());
        assertEquals("Sample Description", reviewEntity.getDescription());
    }

    @Test
    public void testToReviewDtoList() {
        Review review1 = new Review(1, "Title 1", 123, true, "Description 1");
        Review review2 = new Review(2, "Title 2", 456, false, "Description 2");
        List<Review> reviewList = Arrays.asList(review1, review2);
        List<ReviewDto> reviewDtoList = reviewMapper.toReviewDtoList(reviewList);
        assertEquals(2, reviewDtoList.size());
        assertEquals(1L, reviewDtoList.get(0).getId());
        assertEquals("Title 1", reviewDtoList.get(0).getTitle());
        assertEquals(123, reviewDtoList.get(0).getOrderId());
        assertEquals(true, reviewDtoList.get(0).isGrade());
        assertEquals("Description 1", reviewDtoList.get(0).getDescription());
        assertEquals(2L, reviewDtoList.get(1).getId());
        assertEquals("Title 2", reviewDtoList.get(1).getTitle());
        assertEquals(456, reviewDtoList.get(1).getOrderId());
        assertEquals(false, reviewDtoList.get(1).isGrade());
        assertEquals("Description 2", reviewDtoList.get(1).getDescription());
    }
}
