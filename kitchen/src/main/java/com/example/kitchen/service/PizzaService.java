package com.example.kitchen.service;

import com.example.kitchen.dto.OrderDTO;
import com.example.kitchen.dto.OrderForKitchenDTO;
import com.example.kitchen.dto.PizzaListDTO;
import com.example.kitchen.dto.PizzaResponseDTO;
import com.example.kitchen.entity.Pizza;
import com.example.kitchen.mapper.PizzaMapper;
import com.example.kitchen.repository.PizzaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PizzaService {

    private final PizzaRepository pizzaRepository;
    private final RestTemplate restTemplate;



    public Pizza getPizzaById(Integer id) { // Получаем информацию о конкретной пицце по ее идентификатору
        return pizzaRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Не найдена пицца по id = " + id));
    }

    public OrderDTO doCook(OrderForKitchenDTO order) {
        for (Integer pizzaId : order.getPizzaList()) { //сделать проверку есть ли пицца в БД, если нет выкинуть исключение)
            Pizza pizza = getPizzaById(pizzaId);
            pizza.setQuantity(pizza.getQuantity() - 1);
            pizzaRepository.save(pizza);
        }
        // Запускаем процесс готовки пиццы
        return changeOrderStatus(order.getId(), "готовится");
    }

    private OrderDTO changeOrderStatus(int id, String status) {
        URI uri = UriComponentsBuilder.fromHttpUrl("http://localhost:8081/order/set_state/"+id)
                .queryParam("state", "готовится")
                .build().encode().toUri();
        ResponseEntity<OrderDTO> response = restTemplate.postForEntity(uri, null, OrderDTO.class);
        if(!response.getStatusCode().is2xxSuccessful()){
            return response.getBody();
        }

        return null;
    }

    public void increasePizzaQuantity(Integer pizzaId) {
        // Увеличиваем количество пиццы на складе
        pizzaRepository.increasePizzaCount(pizzaId, 10);
    }


    public PizzaListDTO getMenu() {
        List<Pizza> pizzas = pizzaRepository.findAll();
        List<PizzaResponseDTO> list = pizzas.stream().map(PizzaMapper.INSTANCE::toDTO).toList();
        return new PizzaListDTO(list);
    }


}
