package com.example.kitchen.entity;

import jakarta.persistence.*;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Pizza {

    @Id //аннотация обозначает поле как id в БД
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY) //способ генерации значений идентификатора в базе данных (IDENTITY - уникальное значение)
    private int id; //идентификатор пиццы

    @NotBlank // из библиотеки BeanValidation, гарантирует что значение будет notnull и не пустое
    @Column(name = "name", length = 100) //аннотация, указывающая имя колонки в БД и длину
    private String name; //наименование пиццы


    @NotNull //аннотация указывает что поле не может быть null (lombok)
    @Column (name = "quantity", length = 50)
    private int quantity; //поле "количество пиццы"



    /*
    private String description;
    private double price;


    public Pizza(Long id, String name, String description, double price, int quantity) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Pizza{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                '}';
    }

     */
}
