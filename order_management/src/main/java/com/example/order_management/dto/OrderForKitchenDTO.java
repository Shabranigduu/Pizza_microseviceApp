package com.example.order_management.dto;

import lombok.Data;


import java.util.List;

@Data
public class OrderForKitchenDTO {

    private int id;
    private List<Integer> pizzaList;
}
