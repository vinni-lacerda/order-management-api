package com.vinilab.OrderManagement.repositories;

import com.vinilab.OrderManagement.entities.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
