package com.example.feedback.controller;

import com.example.feedback.dto.ReviewDto;
import com.example.feedback.service.ReviewService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;

import static org.mockito.Mockito.*;

@SpringBootTest
public class ReviewControllerTest {


    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(reviewController).build();
        MockitoAnnotations.initMocks(this);
    }

    @Mock
    private ReviewService reviewService;

    @InjectMocks
    private ReviewController reviewController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetReviewsByOrderId() throws Exception {
        int orderId = 123;
        ReviewDto reviewDto = new ReviewDto();
        reviewDto.setOrderId(orderId);

        when(reviewService.getReviewsByOrderId(orderId)).thenReturn(Collections.singletonList(reviewDto));

        mockMvc.perform(MockMvcRequestBuilders.get("/review/{orderId}", orderId))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].orderId").value(orderId));

        verify(reviewService, times(1)).getReviewsByOrderId(orderId);
        verifyNoMoreInteractions(reviewService);
    }

    @Test
    public void testCreateReview() throws Exception {
        ReviewDto reviewDto = new ReviewDto();
        reviewDto.setOrderId(123);

        when(reviewService.createReview(any(ReviewDto.class))).thenReturn(reviewDto);

        mockMvc.perform(MockMvcRequestBuilders.post("/review")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"orderId\":123}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.orderId").value(123));

        verify(reviewService, times(1)).createReview(any(ReviewDto.class));
        verifyNoMoreInteractions(reviewService);
    }

    @Test
    public void testGetAllReviews() throws Exception {
        ReviewDto reviewDto = new ReviewDto();
        reviewDto.setOrderId(123);

        when(reviewService.getAllReviews()).thenReturn(Collections.singletonList(reviewDto));

        mockMvc.perform(MockMvcRequestBuilders.get("/review"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].orderId").value(123));

        verify(reviewService, times(1)).getAllReviews();
        verifyNoMoreInteractions(reviewService);
    }

    @Test
    public void testDeleteReviewsByOrderId() throws Exception {
        int orderId = 123;

        mockMvc.perform(MockMvcRequestBuilders.delete("/review/order/{orderId}", orderId))
                .andExpect(MockMvcResultMatchers.status().isOk());

        verify(reviewService, times(1)).deleteByOrderId(orderId);
        verifyNoMoreInteractions(reviewService);
    }
}

