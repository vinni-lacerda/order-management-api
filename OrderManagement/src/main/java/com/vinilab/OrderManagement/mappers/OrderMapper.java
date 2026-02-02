package com.vinilab.OrderManagement.mappers;

import com.vinilab.OrderManagement.dtos.OrderDTO;
import com.vinilab.OrderManagement.entities.Order;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {

    public OrderMapper() {
    }

    public OrderDTO toDTO(Order entity){
        if(entity == null){
            throw new IllegalArgumentException("Order cannot be null");
        }
        return new OrderDTO(
                entity.getId(),
                entity.getUser().getId(),
                entity.getOrderItems().stream().map(OrderItemMapper::toDTO).toList(),
                entity.getOrderStatus(),
                entity.getTotalPrice(),
                entity.getCreatedAt()
        );
    }
}
