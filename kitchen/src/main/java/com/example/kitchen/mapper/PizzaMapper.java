package com.example.kitchen.mapper;

import com.example.kitchen.dto.PizzaResponseDTO;
import com.example.kitchen.entity.Pizza;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PizzaMapper {
    public final PizzaMapper INSTANCE = Mappers.getMapper(PizzaMapper.class);

    PizzaResponseDTO toDTO(Pizza pizza);

    Pizza toEntity(PizzaResponseDTO dto);

}
