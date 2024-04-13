package com.example.logistic.model.mapper;

import com.example.logistic.model.Delivery;
import com.example.logistic.model.dto.RequestDeliveryDto;
import com.example.logistic.model.dto.ResponseDeliveryDto;

public class DeliveryMapper {
    public static Delivery toEntity(RequestDeliveryDto requestDeliveryDto) {
        return new Delivery(requestDeliveryDto.getCourierId(), requestDeliveryDto.getOrderId());
    }

    public static ResponseDeliveryDto toResponseDto(Delivery delivery) {
        return new ResponseDeliveryDto(delivery.getOrderId(), delivery.getCourierId(), delivery.getIsDelivered());
    }
}
