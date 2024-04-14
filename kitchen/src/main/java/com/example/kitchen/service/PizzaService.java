package com.example.kitchen.service;
import com.example.kitchen.dto.PizzaResponseDTO;
import com.example.kitchen.entity.Pizza;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class PizzaService {

    private static Map<Long, Pizza> pizzaDatabase = new HashMap<>();
    private long orderIdCounter = 1;

    public PizzaService() {
        // Заполняем базу данных пицц
        pizzaDatabase.put(1L, new Pizza(1L, "Маргарита", "Сочная пицца с помидорами и сыром", 10.0, 5));
        pizzaDatabase.put(2L, new Pizza(2L, "Пепперони", "Острая пицца с колбасой пепперони", 12.0, 3));
    }

    public static PizzaResponseDTO getMenu() {
        // Получаем список пицц из базы данных
        return new PizzaResponseDTO(new HashMap<>(pizzaDatabase));
    }

    public PizzaResponseDTO getPizzaById(Long id) {
        // Получаем информацию о конкретной пицце по ее идентификатору
        Pizza pizza = pizzaDatabase.get(id);
        if (pizza != null) {
            return new PizzaResponseDTO(pizza);
        }
        return null;
    }

    public PizzaResponseDTO startCooking() {
        // Запускаем процесс готовки пиццы
        return new PizzaResponseDTO("Готовим вашу пиццу, ожидайте!");
    }

    public PizzaResponseDTO increasePizzaQuantity(Long pizzaId) {
        // Увеличиваем количество пиццы на складе
        Pizza pizza = pizzaDatabase.get(pizzaId);
        if (pizza != null) {
            pizza.setQuantity(pizza.getQuantity() + 1);
            return new PizzaResponseDTO(pizza);
        }
        return null;
    }

    public PizzaResponseDTO notifyOrderReady(Long orderId) {
        // Уведомляем о том, что заказ готов
        orderIdCounter++;
        return new PizzaResponseDTO("Заказ №" + orderId + " готов к выдаче!");
    }
}
