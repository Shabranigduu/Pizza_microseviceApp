package com.example.feedback.repository;

import com.example.feedback.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer> {

    List<Review> findByUserId(int userId);
    void deleteByUserId(int userId);
}
