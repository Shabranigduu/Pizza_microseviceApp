package com.example.feedback.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDto {

    private int id;
    private String title;
    private  int orderId;
    private boolean grade;
    private String description;
    
}