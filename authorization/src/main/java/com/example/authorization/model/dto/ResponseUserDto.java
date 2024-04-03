package com.example.authorization.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ResponseUserDto {
    private int id;
    private String name;
    private String address;
}
