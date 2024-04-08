package com.example.order_management.service;


import com.example.order_management.dto.OrderRequestDTO;
import com.example.order_management.dto.OrderResponseDTO;
import com.example.order_management.dto.UserDTO;
import com.example.order_management.entity.Order;
import com.example.order_management.exception.NotFoundException;
import com.example.order_management.mapper.OrderMapper;
import com.example.order_management.repository.OrderRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    private final RestTemplate restTemplate;

    public List<String> getPizzaList(Order order) {
        List<String> pizzaIdList = order.getPizzaList().stream()
                .map(Object::toString)
                .toList();
        String[] pizzaArray = restTemplate.getForObject("http://localhost:8082/pizza/" + String.join(",", pizzaIdList), String[].class);
        if(pizzaArray == null) return Collections.emptyList();
        return Arrays.asList(pizzaArray);
    }

    public UserDTO getUser(Integer id){
            ResponseEntity<UserDTO> response = restTemplate.getForEntity("http://localhost:8082/user/"+id, UserDTO.class);
       if(response.getStatusCode().is4xxClientError()){
           throw new NotFoundException("Пользователь с id="+id+" не зарегистрирован");
       } else if (response.getStatusCode().is2xxSuccessful()) {
           return response.getBody();
       }else throw new HttpServerErrorException(response.getStatusCode());
    }

    public OrderResponseDTO getOrder(Integer orderId) {
        Optional<Order> optionalOrder = orderRepository.findById(orderId);
        if(optionalOrder.isEmpty()){
            throw new EntityNotFoundException("Заказ с id="+orderId+" не найден");
        }
        return OrderMapper.orderToOrderResponseDTO(optionalOrder.get());
    }


    public OrderResponseDTO addOrder(OrderRequestDTO orderRequestDTO) {
        getUser(orderRequestDTO.getUserId());
        Order order = OrderMapper.orderRequestDTOToEntity(orderRequestDTO);
        return OrderMapper.orderToOrderResponseDTO(orderRepository.save(order));

    }

    public OrderResponseDTO updateOrder(Integer orderId, OrderRequestDTO orderRequestDTO) {
        Order order = getOrderById(orderId);
        order.setPizzaList(orderRequestDTO.getPizzaList());
        return OrderMapper.orderToOrderResponseDTO(orderRepository.save(order));
    }

    public void cancelOrder(Integer orderId) {
        Order order = getOrderById(orderId);
        order.setOrderState(Order.OrderState.CANCELED);
        orderRepository.save(order);
    }

    public OrderResponseDTO setOrderState(Integer orderId, String state) {
        Order order = getOrderById(orderId);
        order.setOrderState(Order.OrderState.fromString(state));
       return OrderMapper.orderToOrderResponseDTO(orderRepository.save(order));
    }

    private Order getOrderById(Integer id){
        Optional<Order> optionalOrder = orderRepository.findById(id);
        if(optionalOrder.isEmpty()){
            throw new NotFoundException("Заказ с id="+id+" не найден.");
        }
        return optionalOrder.get();
    }
}
