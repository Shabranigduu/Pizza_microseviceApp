package com.example.logistic.model.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {

    private int id;

    private int userId;

    private String orderState;

    private List<Integer> pizzaList;

    private LocalDateTime orderDate;
}
