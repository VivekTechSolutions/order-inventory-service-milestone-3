package com.example.milestone_3.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.milestone_3.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
