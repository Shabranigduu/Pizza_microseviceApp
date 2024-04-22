package com.example.order_management.service;

import com.example.order_management.dto.OrderRequestDTO;
import com.example.order_management.dto.OrderResponseDTO;
import com.example.order_management.dto.UserDTO;
import com.example.order_management.entity.Order;
import com.example.order_management.mapper.OrderMapper;
import com.example.order_management.repository.OrderRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private OrderService orderService;

    private final Integer EXIST_USER_ID = 1;

    private final Integer NOT_EXIST_USER_ID = 2;

    private final UserDTO EXIST_USER = UserDTO.builder()
            .id(1)
            .name("Name")
            .address("Address")
            .build();

    private final Order EXIST_ORDER = Order.builder()
            .id(1)
            .userId(1)
            .orderDate(LocalDateTime.of(2024, 4, 10, 12, 12))
            .orderState(Order.OrderState.CREATED)
            .pizzaList(List.of(1, 3, 4, 6))
            .build();

    private final Order COMPLETED_EXIST_ORDER = Order.builder()
            .id(1)
            .userId(1)
            .orderDate(LocalDateTime.of(2024, 4, 10, 12, 12))
            .orderState(Order.OrderState.COMPLETED)
            .pizzaList(List.of(1, 3, 4, 6))
            .build();

    private final OrderRequestDTO NEW_ORDER_REQUEST = OrderRequestDTO.builder()
            .userId(1)
            .pizzaList(List.of(1, 3, 4, 6))
            .build();

    private final Order ORDER_FROM_REQUEST = OrderMapper.orderRequestDTOToEntity(NEW_ORDER_REQUEST);

    private final ResponseEntity<UserDTO> RESPONSE_EXIST_USER = new ResponseEntity<>(new UserDTO(1, "Name", "Address"), HttpStatusCode.valueOf(200));

    private final ResponseEntity<UserDTO> RESPONSE_NOT_EXIST_USER = new ResponseEntity<>(null, HttpStatusCode.valueOf(403));


    @Test
    void getExistUser() {
        Mockito.when(restTemplate.getForEntity("http://authorization:8080/user/" + EXIST_USER_ID, UserDTO.class))
                .thenReturn(RESPONSE_EXIST_USER);
        UserDTO userDTO = orderService.getUser(EXIST_USER_ID);
        assertEquals(EXIST_USER, userDTO);
    }

    @Test
    void getExistOrder() {
        Mockito.when(orderRepository.findById(1))
                .thenReturn(Optional.of(EXIST_ORDER));
        OrderResponseDTO orderResponseDTO = orderService.getOrder(1);
        assertEquals(EXIST_ORDER, OrderMapper.orderResponseDTOToEntity(orderResponseDTO));
        verify(orderRepository).findById(1);
        verify(orderRepository, Mockito.times(1)).findById(1);
    }

    @Test
    void getNotExistOrder() {
        Mockito.when(orderRepository.findById(2))
                .thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> orderService.getOrder(2), "Заказ с id=2 не найден");
        verify(orderRepository).findById(2);
        verify(orderRepository, Mockito.times(1)).findById(2);
    }

    @Test
    void addOrder() {
        Mockito.when(orderRepository.save(ORDER_FROM_REQUEST))
                .thenReturn(EXIST_ORDER);
        Mockito.when(restTemplate.getForEntity("http://authorization:8080/user/" + EXIST_USER_ID, UserDTO.class))
                .thenReturn(RESPONSE_EXIST_USER);
        OrderResponseDTO orderResponseDTO = orderService.addOrder(NEW_ORDER_REQUEST);
        assertEquals(OrderMapper.orderToOrderResponseDTO(EXIST_ORDER), orderResponseDTO);
        verify(orderRepository, Mockito.times(1)).save(Mockito.any());
    }

    @Test
    void updateOrder() {
        Mockito.when(orderRepository.findById(1))
                .thenReturn(Optional.of(EXIST_ORDER));
        Mockito.when(orderRepository.save(EXIST_ORDER))
                .thenReturn(EXIST_ORDER);
        OrderResponseDTO orderResponseDTO = orderService.updateOrder(1, NEW_ORDER_REQUEST);
        assertEquals(OrderMapper.orderToOrderResponseDTO(EXIST_ORDER), orderResponseDTO);
        verify(orderRepository).findById(1);
        verify(orderRepository).save(EXIST_ORDER);
        verify(orderRepository, times(1)).save(Mockito.any());
    }

    @Test
    void cancelOrder() {
        Mockito.when(orderRepository.findById(1))
                .thenReturn(Optional.of(EXIST_ORDER));
        orderService.cancelOrder(1);
        verify(orderRepository).findById(1);
        verify(orderRepository).save(EXIST_ORDER);
        verify(orderRepository, times(1)).save(Mockito.any());
    }

    @Test
    void setOrderState() {
        Mockito.when(orderRepository.findById(1))
                .thenReturn(Optional.of(EXIST_ORDER));
        Mockito.when(orderRepository.save(COMPLETED_EXIST_ORDER))
                .thenReturn(COMPLETED_EXIST_ORDER);
        OrderResponseDTO orderResponseDTO = orderService.setOrderState(1, Order.OrderState.COMPLETED.getState());
        assertEquals(OrderMapper.orderToOrderResponseDTO(COMPLETED_EXIST_ORDER), orderResponseDTO);
        verify(orderRepository).findById(1);
        verify(orderRepository).save(COMPLETED_EXIST_ORDER);
        verify(orderRepository, times(1)).save(Mockito.any());
    }
}