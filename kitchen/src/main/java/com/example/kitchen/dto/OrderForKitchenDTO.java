package com.example.kitchen.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderForKitchenDTO {

    private int id;
    private List<Integer> pizzaList;
}