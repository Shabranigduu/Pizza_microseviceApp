package com.example.kitchen.controller;

import com.example.kitchen.dto.*;
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
    //GET запрос меню в базу данных кухни перед началом исполнения заказа, так сервис заказов узнает какие пиццы есть в меню
    public PizzaListDTO getMenu() {
        return pizzaService.getMenu();
    }

    /**
     * POST запрос на исполнение заказа от order_management.
     * Метод принимает JSON-объект с информацией о заказе для кухни и возвращает информацию о статусе заказа.
     * Если пицца для заказа отсутствует в базе данных, обрабатывает исключение с помощью Exception Handler.
     *
     * @param order JSON-объект с информацией о заказе для кухни
     * @return информация о статусе заказа
     */
    @PostMapping("/do_cook")
    //POST запрос на исполнение заказа от order_management (возвратить статус заказа, обработать исключение если нет пиццы в БД Exception Handler)
    public OrderDTO doCook(@RequestBody OrderForKitchenDTO order) {
        return pizzaService.doCook(order);

    }

    /**
     * POST запрос для добавления пиццы, если она отсутствует в базе данных.
     * Метод принимает ID пиццы в качестве параметра и увеличивает количество этой пиццы в базе данных.
     *
     * @param pizzaId ID пиццы, которую необходимо добавить
     */
    @PostMapping("/{pizzaId}")
    public void increasePizzaQuantity(@PathVariable Integer pizzaId) {
        pizzaService.increasePizzaQuantity(pizzaId);

    }

    @PostMapping
    public PizzaResponseDTO addPizza(@RequestBody PizzaRequestDTO pizzaRequestDTO){
        return pizzaService.addPizza(pizzaRequestDTO);
    }

    /**
     * GET запрос для получения информации о пицце по её идентификатору.
     * Метод принимает ID пиццы в качестве пути и возвращает информацию о пицце с указанным ID.
     *
     * @param pizzaId Идентификатор пиццы
     * @return Объект класса Pizza с информацией о пицце
     */
    @GetMapping("/{pizzaId}")
    public PizzaResponseDTO getPizzaById(@PathVariable Integer pizzaId) {
        return pizzaService.getPizzaById(pizzaId);
    }
}

