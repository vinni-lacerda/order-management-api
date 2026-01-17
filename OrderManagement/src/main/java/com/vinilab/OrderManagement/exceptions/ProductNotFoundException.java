package com.vinilab.OrderManagement.exceptions;

public class ProductNotFoundException extends ResourceNotFoundException {
    public ProductNotFoundException(Long id) {
        super("Product not found with id: " + id);
    }
}
