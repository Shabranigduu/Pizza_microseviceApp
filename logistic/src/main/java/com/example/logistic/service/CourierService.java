package com.example.logistic.service;

import com.example.logistic.model.dto.RequestCourierDto;
import com.example.logistic.model.dto.ResponseCourierDto;

import java.util.List;

public interface CourierService {
    ResponseCourierDto postCourier(RequestCourierDto requestCourierDto);

    ResponseCourierDto getCourier(Integer courierId);

    ResponseCourierDto patchCourier(Integer courierId, RequestCourierDto requestCourierDto);

    void deleteCourier(Integer courierId);

    List<ResponseCourierDto> getCouriers();

}
