package com.example.order_management.service;

import com.example.order_management.dto.UserDTO;
import com.example.order_management.entity.Order;
import com.example.order_management.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
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
            .orderDate(LocalDateTime.now())
            .orderState(Order.OrderState.CREATED)
            .pizzaList(List.of(1,3,4,6))
            .build();

    private final Order NOT_EXIST_ORDER = null;

    private final ResponseEntity<UserDTO> RESPONSE_EXIST_USER = new ResponseEntity<>(new UserDTO(1, "Name", "Address"), HttpStatusCode.valueOf(200));

    private final ResponseEntity<UserDTO> RESPONSE_NOT_EXIST_USER = new ResponseEntity<>(null, HttpStatusCode.valueOf(403));


    @Test
    void getExistUser() {
        Mockito.when(restTemplate.getForEntity("http://localhost:8082/user/"+EXIST_USER_ID, UserDTO.class))
                .thenReturn(RESPONSE_EXIST_USER);
        UserDTO userDTO = orderService.getUser(EXIST_USER_ID);
        assertEquals(EXIST_USER, userDTO);
    }

    @Test
    void getOrder() {
    }

    @Test
    void addOrder() {
    }

    @Test
    void updateOrder() {
    }

    @Test
    void cancelOrder() {
    }

    @Test
    void setOrderState() {
    }
}