package com.example.logistic.controller;

import com.example.logistic.model.dto.RequestCourierDto;
import com.example.logistic.model.dto.ResponseCourierDto;
import com.example.logistic.service.CourierService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/courier")
public class CourierController {
    private final CourierService courierService;

    @PostMapping()
    public ResponseCourierDto postCourier(@RequestBody @Valid RequestCourierDto requestCourierDto) {
        return courierService.postCourier(requestCourierDto);
    }

    @GetMapping("/{courierId}")
    public ResponseCourierDto getCourier(@PathVariable Integer courierId) {
        return courierService.getCourier(courierId);
    }

    @PatchMapping("/{courierId}")
    public ResponseCourierDto patchCourier(@PathVariable Integer courierId, @RequestBody RequestCourierDto requestCourierDto) {
        return courierService.patchCourier(courierId, requestCourierDto);
    }

    @DeleteMapping("/{courierId}")
    public void deleteCourier(@PathVariable Integer courierId) {
        courierService.deleteCourier(courierId);
    }

}
