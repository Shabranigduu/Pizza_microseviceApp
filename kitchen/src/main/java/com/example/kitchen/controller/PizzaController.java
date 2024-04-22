package com.example.kitchen.controller;

import com.example.kitchen.dto.OrderForKitchenDTO;
import com.example.kitchen.dto.PizzaListDTO;
import com.example.kitchen.service.PizzaService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController //аккумулирует поведение двух аннотаций @Controller и @ResponseBody
@RequestMapping("/pizza") //для маппинга урл-адреса запроса на указанный метод или класс
public class PizzaController {

    private final PizzaService pizzaService;

    public PizzaController(PizzaService pizzaService) {
        this.pizzaService = pizzaService;
    }


    @GetMapping(value = "/menu", produces = MediaType.APPLICATION_JSON_VALUE)
    //GET запрос меню в базу данных перед началом исполнения заказа
    public PizzaListDTO getMenu() {
        return pizzaService.getMenu();
    }


    @PostMapping("/do_cook")       //POST запрос в БД для начала исполнения заказа на пиццу
    public void doCook(@RequestBody OrderForKitchenDTO order) {
        pizzaService.doCook(order);
    }

    @PutMapping("/{pizzaId}")  // PUT запрос в базу данных, увеличить количество пиццы в заказе
    public void increaseQuantity(@PathVariable Integer pizzaId) {
        pizzaService.increasePizzaQuantity(pizzaId);
    }
}
