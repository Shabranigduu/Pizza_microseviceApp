package com.example.kitchen.controller;

import com.example.kitchen.dto.PizzaResponseDTO;
import com.example.kitchen.service.PizzaService;
import org.springframework.web.bind.annotation.*;

@RestController //аккумулирует поведение двух аннотаций @Controller и @ResponseBody
@RequestMapping("/pizza") //для маппинга урл-адреса запроса на указанный метод или класс
public class PizzaController {

    private final PizzaService pizzaService;

    public PizzaController(PizzaService pizzaService) {
        this.pizzaService = pizzaService;
    }


    @GetMapping("/menu") //GET запрос меню в базу данных перед началом исполнения заказа
    public PizzaResponseDTO getMenu() {
        return PizzaService.getMenu();
    }

    @GetMapping ("/{id}") //Информация о пицце
    public PizzaResponseDTO pizzaInfo(@PathVariable Long id) {
        return pizzaService.getPizzaById(id);
    }

    @PostMapping("/do_cook")       //POST запрос в БД для начала исполнения заказа на пиццу
    public PizzaResponseDTO doCook() {
        return pizzaService.startCooking();
    }

    @PutMapping ("/{pizzaId}")  // PUT запрос в базу данных, увеличить количество пиццы в заказе
    public PizzaResponseDTO increaseQuantity(@PathVariable Long pizzaId) {
        return pizzaService.increasePizzaQuantity(pizzaId);
    }

    @PostMapping ("/{orderId") // POST запрос в микросервис "доставка" и микросервис "управление заказами" о готовности заказа
    public PizzaResponseDTO isReady(@PathVariable Long orderId) {
        return pizzaService.notifyOrderReady(orderId);
    }





}
