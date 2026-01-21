package com.vinilab.OrderManagement.exceptions;

public class OrderItemNotFoundException extends ResourceNotFoundException {
    public OrderItemNotFoundException(Long id) {
        super("OrderItem not found with id: " + id);
    }
}
