package com.example.authorization.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
public class RequestUserDto {
    @NotBlank
    private String name;
    @NotBlank
    private String address;
}
