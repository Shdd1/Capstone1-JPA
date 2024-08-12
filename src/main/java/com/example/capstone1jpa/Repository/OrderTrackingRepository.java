package com.example.capstone1jpa.Repository;

import com.example.capstone1jpa.Model.OrderTracking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderTrackingRepository extends JpaRepository<OrderTracking,Integer> {
}
