package com.example.logistic.model.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class RequestCourierDto {
    @NotBlank
    private String name;
    @NotNull
    @Min(18)
    @Max(80)
    private Integer age;
}
