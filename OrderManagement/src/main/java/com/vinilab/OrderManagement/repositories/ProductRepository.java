package com.vinilab.OrderManagement.repositories;

import com.vinilab.OrderManagement.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
