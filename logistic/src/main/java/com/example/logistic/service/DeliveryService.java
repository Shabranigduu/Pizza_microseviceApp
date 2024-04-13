package com.example.logistic.service;


import com.example.logistic.model.dto.ResponseDeliveryDto;

public interface DeliveryService {

    ResponseDeliveryDto postDelivery(Integer orderId);

    ResponseDeliveryDto patchDelivery(Integer orderId);

}
