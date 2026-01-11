package com.vinilab.OrderManagement.repositories;

import com.vinilab.OrderManagement.entities.Order;
import com.vinilab.OrderManagement.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
