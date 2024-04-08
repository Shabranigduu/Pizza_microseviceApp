package com.example.order_management.dto;


import lombok.Builder;
import lombok.Data;

import org.springframework.lang.NonNull;

import java.util.List;

@Data
@Builder
public class OrderRequestDTO {

    @NonNull
    private int userId;

    @NonNull
    private List<Integer> pizzaList;
}
