package com.example.logistic.model.mapper;

import com.example.logistic.model.Courier;
import com.example.logistic.model.dto.RequestCourierDto;
import com.example.logistic.model.dto.ResponseCourierDto;

public class CourierMapper {
    public static Courier toEntity(RequestCourierDto courierDto) {
        return new Courier(courierDto.getName(), courierDto.getAge());
    }

    public static ResponseCourierDto toResponseDto(Courier courier) {
        return new ResponseCourierDto(courier.getId(), courier.getName(), courier.getAge());
    }
}
