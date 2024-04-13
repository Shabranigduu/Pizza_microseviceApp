package com.example.logistic.repository;

import com.example.logistic.model.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliveryRepository extends JpaRepository<Delivery, Integer> {

    @Modifying
    @Query(nativeQuery = true, value = "UPDATE delivery SET is_delivered = true WHERE delivery.order_id = ?1")
    void updateDeliveryStateByOrderId(Integer orderId);

    Delivery getDeliveryByOrderId(Integer orderId);
}
