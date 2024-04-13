package com.example.logistic.repository;

import com.example.logistic.model.Courier;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourierRepository extends JpaRepository<Courier, Integer> {
    List<Courier> findAll();
}
