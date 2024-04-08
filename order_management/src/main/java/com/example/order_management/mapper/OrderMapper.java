package com.example.order_management.mapper;

import com.example.order_management.dto.OrderForLogisticDTO;
import com.example.order_management.dto.OrderRequestDTO;
import com.example.order_management.dto.OrderResponseDTO;
import com.example.order_management.dto.UserDTO;
import com.example.order_management.entity.Order;
import com.example.order_management.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class OrderMapper {

    private final OrderService orderService;

    public OrderForLogisticDTO toOrderForLogisticDTO(Order order){
        UserDTO user = orderService.getUser(order.getId());
        return OrderForLogisticDTO.builder()
                .id(order.getId())
                .clientName(user.getName())
                .clientAddress(user.getAddress())
                .build();
    }

    public static Order orderRequestDTOToEntity(OrderRequestDTO orderRequestDTO){
        return Order.builder()
                .userId(orderRequestDTO.getUserId())
                .pizzaList(orderRequestDTO.getPizzaList())
                .build();
    }

    public static OrderResponseDTO orderToOrderResponseDTO(Order order){
        return OrderResponseDTO.builder()
                .id(order.getId())
                .userId(order.getUserId())
                .orderState(order.getOrderState().getState())
                .orderDate(order.getOrderDate())
                .pizzaList(order.getPizzaList())
                .build();
    }

}
