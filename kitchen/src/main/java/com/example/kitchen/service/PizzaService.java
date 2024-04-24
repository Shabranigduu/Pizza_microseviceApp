package com.example.kitchen.service;

import com.example.kitchen.dto.OrderDTO;
import com.example.kitchen.dto.OrderForKitchenDTO;
import com.example.kitchen.dto.PizzaListDTO;
import com.example.kitchen.dto.PizzaResponseDTO;
import com.example.kitchen.entity.Pizza;
import com.example.kitchen.exception.NotEnoughPizzasAvailableException;
import com.example.kitchen.exception.NotFoundException;
import com.example.kitchen.exception.PizzaNotFoundException;
import com.example.kitchen.mapper.PizzaMapper;
import com.example.kitchen.repository.PizzaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PizzaService {


    private final RestTemplate restTemplate;

    private final PizzaRepository pizzaRepository;

    public PizzaResponseDTO getPizzaById(Integer id) {
        // Получаем информацию о конкретной пицце по ее идентификатору
        Pizza pizza = pizzaRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Не найдена пицца по id = " + id));
        return PizzaMapper.INSTANCE.toDTO(pizza);
    }

    public OrderDTO doCook(OrderForKitchenDTO order) {

        /*-посчитать какое количество каких пицц заказано;
          -сравнить количество пицц с пиццами в БД
          -если пицц в БД меньше, выкинуть исключение Exception Handler, разобраться как он работает
          -создать свою ошибку, добавить в Exception Handler
          -при выбрасывании ошибки Handler отправит ResponseEntity указанный в Handler c статусом и сообщением
          -дали совет сделать логику через List stream, сделать группировку по id
          -в каждом методе внутри стрима сделать запрос из БД, получить кол-во пицц по этому id и там сравнить

         */
        Map<Integer, Long> pizzaMap = order.getPizzaList().stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        pizzaMap.keySet().forEach(pizzaId -> {
            Pizza pizza = pizzaRepository.findById(pizzaId).orElseThrow(() ->
                    new NotEnoughPizzasAvailableException("Pizza with id=" + pizzaId + " not found"));
            int availablePizzas = pizza.getQuantity();
            if (availablePizzas < pizzaMap.get(pizzaId)) {
                throw new PizzaNotFoundException("Pizza with id=" + pizzaId + " does not have enough items.");
            }
            pizza.setQuantity((int) (availablePizzas - pizzaMap.get(pizzaId)));
            pizzaRepository.save(pizza);
        });

        // Запускаем процесс готовки пиццы
        return changeOrderStatus(order.getId());
    }

    private OrderDTO changeOrderStatus(int id) {
        URI uri = UriComponentsBuilder.fromHttpUrl("http://localhost:8081/order/set_state/" + id)
                .queryParam("state", "готовится")
                .build().encode().toUri();
        ResponseEntity<OrderDTO> response = restTemplate.postForEntity(uri, null, OrderDTO.class);
        if (!response.getStatusCode().is2xxSuccessful()) {
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
