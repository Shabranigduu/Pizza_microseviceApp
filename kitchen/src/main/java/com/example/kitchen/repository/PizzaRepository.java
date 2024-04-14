package com.example.kitchen.repository;
import com.example.kitchen.entity.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;

    public interface PizzaRepository extends JpaRepository<Pizza, Long> {
        // Код в разработке
    }

