package com.example.logistic.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDeliveryDto {
    private Integer orderId;
    private Integer courierId;
    private Boolean isDelivered;
}
