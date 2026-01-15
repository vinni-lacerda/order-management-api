package com.vinilab.OrderManagement.mappers;

import com.vinilab.OrderManagement.dtos.OrderItemDTO;
import com.vinilab.OrderManagement.entities.OrderItem;

public class OrderItemMapper {

    public static OrderItemDTO toDTO(OrderItem entity){
        if(entity == null){
            throw new IllegalArgumentException("Entity cannot be null");
        }

        return new OrderItemDTO(
                entity.getId(),
                entity.getQuantity(),
                entity.getProduct().getId(),
                entity.getUnitPrice(),
                entity.getSubTotal()
        );
    }
}
