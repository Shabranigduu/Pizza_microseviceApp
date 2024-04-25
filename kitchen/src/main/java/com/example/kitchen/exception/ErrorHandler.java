package com.example.kitchen.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ErrorHandler {
//    @ExceptionHandler
//    @ResponseStatus(HttpStatus.NOT_FOUND)
//    public ApiError notFoundException(final NotFoundException e) {
//        return new ApiError(HttpStatus.NOT_FOUND.toString(),
//                "The required object was not found.",
//                e.getMessage(),
//                LocalDateTime.now());
//    }
//
//    @ExceptionHandler
//    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//    public ApiError throwableException(final Throwable e) {
//        return new ApiError(HttpStatus.INTERNAL_SERVER_ERROR.toString(),
//                "internal server error",
//                e.getMessage(),
//                LocalDateTime.now());
//    }
//
//    @ExceptionHandler(PizzaNotFoundException.class)
//    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//    public ApiError pizzaNotFoundHandler(PizzaNotFoundException e) {
//        return new ApiError(HttpStatus.INTERNAL_SERVER_ERROR.toString(),
//                "did not find a pizza with given id",
//                e.getMessage(),
//                LocalDateTime.now());
//    }
//
//    @ExceptionHandler(NotEnoughPizzasAvailableException.class)
//    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//    public ApiError pizzaNotEnoughHandler(NotEnoughPizzasAvailableException e) {
//        return new ApiError(HttpStatus.INTERNAL_SERVER_ERROR.toString(),
//                "not enough pizzas of given type",
//                e.getMessage(),
//                LocalDateTime.now());
//    }
}