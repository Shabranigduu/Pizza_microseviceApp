package com.example.order_management.dto;


import lombok.*;
import org.springframework.lang.NonNull;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderRequestDTO {

    @NonNull
    private int userId;

    private List<Integer> pizzaList;

}
