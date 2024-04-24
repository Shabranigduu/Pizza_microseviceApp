package com.example.kitchen.controller;

import com.example.kitchen.dto.OrderDTO;
import com.example.kitchen.dto.OrderForKitchenDTO;
import com.example.kitchen.dto.PizzaListDTO;
import com.example.kitchen.entity.Pizza;
import com.example.kitchen.service.PizzaService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pizza")
public class PizzaController {

    private final PizzaService pizzaService;

    public PizzaController(PizzaService pizzaService) {
        this.pizzaService = pizzaService;
    }

    /**
     * Метод для получения меню из базы данных кухни перед началом выполнения заказа.
     *
     * @return DTO объект, содержащий список доступных пицц
     */
    @GetMapping(value = "/menu")
    //GET запрос меню в базу данных кухни перед началом исполнения заказа (из Postman)
    public PizzaListDTO getMenu() {
        return pizzaService.getMenu();
    }


    @PostMapping("/do_cook")
    //POST запрос на исполнение заказа от order_management (возвратить статус заказа, обработать исключение если нет пиццы в БД Exception Handler)
    public OrderDTO doCook(@RequestBody OrderForKitchenDTO order) {
        return pizzaService.doCook(order);

    }

    @PostMapping()
    public void addPizzas(@RequestBody Integer pizzaId) { //добавить пиццу если не хватает в БД
        pizzaService.increasePizzaQuantity(pizzaId);
    }

    @GetMapping("/{pizzaId}")
    public Pizza getPizzaById(@PathVariable Integer pizzaId) { // вернуть инфо по каждой пицце
        return pizzaService.getPizzaById(pizzaId);
    }
}

