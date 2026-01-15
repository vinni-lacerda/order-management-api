package com.vinilab.OrderManagement.mappers;

import com.vinilab.OrderManagement.dtos.OrderDTO;
import com.vinilab.OrderManagement.entities.Order;

public class OrderMapper {

    public OrderMapper() {
    }

    public Order toEntity(OrderDTO dto){
        if(dto == null){
            throw new IllegalArgumentException("OrderDTO cannot be null");
        }

        return new Order(
                dto.getId(),
                dto.getUserId(),
                dto.getTotalPrice(),
                dto.getCreatedAt(),
                dto.getOrderItems(),
                dto.getOrderStatus(),
        );
    }

    public OrderDTO toDTO(Order entity){
        if(entity == null){
            throw new IllegalArgumentException("Order cannot be null");
        }
        return new OrderDTO(
                entity.getId(),
                entity.getUser(),
                entity.getCreatedAt(),
                entity.getOrderItems(),
                entity.getOrderStatus(),
                entity.getTotalPrice()
        );
    }
}
