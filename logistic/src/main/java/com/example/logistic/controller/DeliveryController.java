package com.example.logistic.controller;

import com.example.logistic.model.dto.ResponseDeliveryDto;
import com.example.logistic.service.DeliveryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/delivery")
@RestController
public class DeliveryController {
    private final DeliveryService deliveryService;

    @PostMapping("/{orderId}")
    public ResponseDeliveryDto postDelivery(@PathVariable Integer orderId) { // уточнить какой dto будет на входе из кухни
        return deliveryService.postDelivery(orderId);
    }

    @PatchMapping("{orderId}")
    public ResponseDeliveryDto patchDeliveryStatus(@PathVariable Integer orderId) {
        return deliveryService.patchDelivery(orderId);
    }
}
