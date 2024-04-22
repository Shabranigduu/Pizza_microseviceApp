package com.example.kitchen.repository;

import com.example.kitchen.entity.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PizzaRepository extends JpaRepository<Pizza, Integer> {
    Optional<Pizza> findByNameAndQuntityLessThan(String name, Integer quantity);

    @Query("UPDATE Pizza p SET p.quantity = p.quantity + :count WHERE p.id = :id")
    void increasePizzaCount(Integer id, Integer count);

}

