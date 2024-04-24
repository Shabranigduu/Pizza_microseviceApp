package com.example.kitchen.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Pizza {

    @Id //аннотация обозначает поле как id в БД
    @GeneratedValue(strategy = GenerationType.IDENTITY) //способ генерации значений идентификатора в базе данных (IDENTITY - уникальное значение)
    private int id; //идентификатор пиццы

    @NotBlank // из библиотеки BeanValidation, гарантирует что значение будет notnull и не пустое
    @Column(name = "name", length = 100) //аннотация, указывающая имя колонки в БД и длину
    private String name; //наименование пиццы

    @Column (name = "quantity", length = 50)
    private int quantity; //поле "количество пиццы"
}
