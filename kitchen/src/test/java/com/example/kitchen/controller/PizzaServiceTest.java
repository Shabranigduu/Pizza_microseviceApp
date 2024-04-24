package com.example.kitchen.controller;

import com.example.kitchen.dto.PizzaResponseDTO;
import com.example.kitchen.entity.Pizza;
import com.example.kitchen.repository.PizzaRepository;
import com.example.kitchen.service.PizzaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PizzaServiceTest {

    private PizzaService pizzaService;
    @Mock
    private PizzaRepository pizzaRepository;

    private RestTemplate restTemplate = new RestTemplate();

    @BeforeEach
    void setUp() {
        pizzaService = new PizzaService(restTemplate, pizzaRepository);
    }

    @Test
    void getPizzaById_success() {
        var pizza = new Pizza();
        pizza.setName("pizza1");
        pizza.setQuantity(7);
        pizza.setId(1);
        when(pizzaRepository.findById(anyInt())).thenReturn(Optional.of(pizza));

        PizzaResponseDTO pizzaResponseDTO = pizzaService.getPizzaById(1);

        assertEquals(1, pizzaResponseDTO.getId(), "Expected id to be 1");
        assertEquals("pizza1", pizzaResponseDTO.getName(), "Expected name to be 'pizza1'");
        assertEquals(7, pizzaResponseDTO.getQuantity(), "Expected quantity to be 7");
    }
}
