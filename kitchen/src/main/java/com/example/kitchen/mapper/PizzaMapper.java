package com.example.kitchen.mapper;

import com.example.kitchen.dto.PizzaResponseDTO;
import com.example.kitchen.entity.Pizza;

public class PizzaMapper {

    public static PizzaResponseDTO toDTO(Pizza pizza) {
        PizzaResponseDTO dto = new PizzaResponseDTO();
        dto.setId(pizza.getId());
        dto.setName(pizza.getName());
        dto.setDescription(pizza.getDescription());
        dto.setPrice(pizza.getPrice());
        dto.setQuantity(pizza.getQuantity());
        return dto;
    }

    public static Pizza toEntity(PizzaResponseDTO dto) {
        Pizza pizza = new Pizza();
        pizza.setId(dto.getId());
        pizza.setName(dto.getName());
        pizza.setDescription(dto.getDescription());
        pizza.setPrice(dto.getPrice());
        pizza.setQuantity(dto.getQuantity());
        return pizza;
    }

}
