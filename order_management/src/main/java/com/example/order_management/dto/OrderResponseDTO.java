package com.example.order_management.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class OrderResponseDTO {

    private int id;

    private int userId;

    private String orderState;

    private List<Integer> pizzaList;

    private LocalDateTime orderDate;
}
