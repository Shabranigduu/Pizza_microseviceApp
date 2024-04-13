package com.example.logistic.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "delivery")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Delivery {
    @Id
    private Integer orderId;
    private Integer courierId;
    @Column(columnDefinition = "BOOLEAN DEFAULT false")
    private Boolean isDelivered;

    public Delivery(Integer courierId, Integer orderId) {
        this.courierId = courierId;
        this.orderId = orderId;
        this.isDelivered = false;
    }
}
