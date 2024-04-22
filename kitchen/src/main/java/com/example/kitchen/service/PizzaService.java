package com.example.kitchen.service;

import com.example.kitchen.dto.OrderForKitchenDTO;
import com.example.kitchen.dto.PizzaListDTO;
import com.example.kitchen.dto.PizzaResponseDTO;
import com.example.kitchen.entity.Pizza;
import com.example.kitchen.mapper.PizzaMapper;
import com.example.kitchen.repository.PizzaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PizzaService {

    private final PizzaRepository pizzaRepository;
    private final RestTemplate restTemplate;


    public Pizza getPizzaById(Integer id) {
        // Получаем информацию о конкретной пицце по ее идентификатору
        Pizza pizza = pizzaRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Не найдена пицца по id = " + id));

        return pizza;
    }

    public void doCook(OrderForKitchenDTO order) {
        for (Integer pizzaId : order.getPizzaList()) {
            Pizza pizza = getPizzaById(pizzaId);
            pizza.setQuantity(pizza.getQuantity() - 1);
            pizzaRepository.save(pizza);
        }
        // Запускаем процесс готовки пиццы
        changeOrderStatus(order.getId(), "COOKING");
    }

    private void changeOrderStatus(int id, String status) {
        restTemplate.postForEntity(
                URI.create(String.format("http://localhost:8081/order/%d/%s", id, status)),
                null,
                null
        );
    }

    public void increasePizzaQuantity(Integer pizzaId) {
        // Увеличиваем количество пиццы на складе
        pizzaRepository.increasePizzaCount(pizzaId, 10);
    }


    public PizzaListDTO getMenu() {
        List<Pizza> pizzas = pizzaRepository.findAll();
        List<PizzaResponseDTO> list = pizzas.stream().map(x -> PizzaMapper.INSTANCE.toDTO(x)).toList();
        return new PizzaListDTO(list);
    }


}
