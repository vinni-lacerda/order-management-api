package com.vinilab.OrderManagement.exceptions;

public class UserNotFoundException extends ResourceNotFoundException {
    public UserNotFoundException(Long id) {
        super("Product not found with id: " + id);
    }
}
