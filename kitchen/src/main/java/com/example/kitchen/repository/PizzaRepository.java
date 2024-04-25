package com.example.kitchen.repository;

import com.example.kitchen.entity.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PizzaRepository extends JpaRepository<Pizza, Integer> {
    Optional<Pizza> findByNameAndQuantityLessThan(String name, Integer quantity);

    @Modifying
    @Query("UPDATE Pizza p SET p.quantity = p.quantity + :count WHERE p.id = :id")
    int increasePizzaCount(@Param("id") Integer id, @Param("count") Integer count);

}

