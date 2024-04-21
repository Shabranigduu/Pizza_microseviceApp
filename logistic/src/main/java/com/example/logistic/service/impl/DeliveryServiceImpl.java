package com.example.logistic.service.impl;

import com.example.logistic.client.Client;
import com.example.logistic.model.Delivery;
import com.example.logistic.model.dto.ResponseCourierDto;
import com.example.logistic.model.dto.ResponseDeliveryDto;
import com.example.logistic.model.mapper.DeliveryMapper;
import com.example.logistic.repository.DeliveryRepository;
import com.example.logistic.service.CourierService;
import com.example.logistic.service.DeliveryService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;


@Service
@RequiredArgsConstructor
public class DeliveryServiceImpl implements DeliveryService {
    private final DeliveryRepository deliveryRepository;
    private final CourierService courierService;
    private final Client client;

    private final String DELIVERED_STATE = "доставляется";
    private final String COMPLETED_STATE = "выполнен";

    @Override
    public ResponseDeliveryDto postDelivery(Integer orderId) {
        Delivery delivery = new Delivery(orderId, chooseCourier());
        client.sendStatusOrder(orderId, DELIVERED_STATE);
        return DeliveryMapper.toResponseDto(deliveryRepository.save(delivery));
    }

    @Transactional
    @Override
    public ResponseDeliveryDto patchDelivery(Integer orderId) {
        deliveryRepository.updateDeliveryStateByOrderId(orderId);
        Delivery delivery = deliveryRepository.getDeliveryByOrderId(orderId);
        client.sendStatusOrder(orderId, COMPLETED_STATE);
        return DeliveryMapper.toResponseDto(delivery);
    }

    private int chooseCourier() {
        List<ResponseCourierDto> couriers = courierService.getCouriers();
        Random random = new Random();

        int max = couriers.size();
        int randomInt = random.nextInt(max);

        return couriers.get(randomInt).getId();
    }
}
