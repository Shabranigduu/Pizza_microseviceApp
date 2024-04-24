package com.example.kitchen.exception;

public class NotEnoughPizzasAvailableException extends RuntimeException {
    public NotEnoughPizzasAvailableException(String message) {
        super(message);
    }
}
