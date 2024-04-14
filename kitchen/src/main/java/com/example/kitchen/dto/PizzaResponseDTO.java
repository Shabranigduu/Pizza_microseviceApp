package com.example.kitchen.dto;
import java.util.Map;
public class PizzaResponseDTO {

    private Object data;

    public PizzaResponseDTO(Object data) {
        this.data = data;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
