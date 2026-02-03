package com.example.milestone_3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.milestone_3.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
