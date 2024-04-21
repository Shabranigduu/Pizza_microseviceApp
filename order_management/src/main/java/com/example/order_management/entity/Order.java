package com.example.order_management.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.NonNull;

import java.time.LocalDateTime;
import java.util.List;


@ToString
@EqualsAndHashCode
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "pizza-order")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NonNull
    private int userId;
    @Enumerated(EnumType.STRING)
    private OrderState orderState;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<Integer> pizzaList;

    @DateTimeFormat(style = "hh:mm dd.MM.yyyy")
    private LocalDateTime orderDate;

    @PrePersist
    protected void onCreate() {
        if (orderDate == null) {
            orderDate = LocalDateTime.now();
        }
        if (orderState == null) {
            orderState = OrderState.CREATED;
        }
    }

    public enum OrderState {
        CREATED("создан"),
        COOKING("готовится"),
        DELIVERED("доставляется"),
        CANCELED("отменен"),
        COMPLETED("выполнен");

        private final String state;

        // Конструктор для перечисления
        OrderState(String state) {
            this.state = state;
        }

        public String getState() {
            return state;
        }

        // Получить значение enum по строковому представлению
        public static OrderState fromString(String text) {
            for (OrderState b : OrderState.values()) {
                if (b.state.equalsIgnoreCase(text)) {
                    return b;
                }
            }
            throw new IllegalArgumentException("Невозможно найти значение для: " + text);
        }
    }
}
