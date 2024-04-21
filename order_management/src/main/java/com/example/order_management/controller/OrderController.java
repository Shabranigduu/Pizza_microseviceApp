package com.example.order_management.controller;

import com.example.order_management.dto.OrderRequestDTO;
import com.example.order_management.dto.OrderResponseDTO;
import com.example.order_management.service.OrderService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/order")
public class OrderController {


    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/{orderId}")
    public OrderResponseDTO getOrder(@PathVariable Integer orderId) {
        return orderService.getOrder(orderId);
    }

    @PostMapping()
    public OrderResponseDTO addOrder(@RequestBody @Valid OrderRequestDTO orderRequestDTO) {
        return orderService.addOrder(orderRequestDTO);
    }

    @PatchMapping("/{orderId}")
    public OrderResponseDTO updateOrder(@RequestBody @Valid OrderRequestDTO orderRequestDTO,
                                        @PathVariable Integer orderId) {
        return orderService.updateOrder(orderId, orderRequestDTO);
    }

    @DeleteMapping("{orderId}")
    public void cancelOrder(@PathVariable Integer orderId) {
        orderService.cancelOrder(orderId);
    }

    @PostMapping("/set_state/{orderId}")
    public OrderResponseDTO setOrderState(@PathVariable Integer orderId,
                                          @RequestParam(name = "state") String state) {
        return orderService.setOrderState(orderId, state);
    }
}
