package com.example.order_management.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderForLogisticDTO {

    private int id;
    private String clientName;
    private String clientAddress;
}
