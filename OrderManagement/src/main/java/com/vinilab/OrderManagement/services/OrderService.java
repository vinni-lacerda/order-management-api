package com.vinilab.OrderManagement.services;

import com.vinilab.OrderManagement.dtos.OrderDTO;
import com.vinilab.OrderManagement.entities.Order;
import com.vinilab.OrderManagement.exceptions.OrderNotFoundException;
import com.vinilab.OrderManagement.mappers.OrderMapper;
import com.vinilab.OrderManagement.repositories.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Service
@Transactional
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    public OrderService(OrderRepository orderRepository, OrderMapper orderMapper) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
    }

    @GetMapping
    public List<OrderDTO> findAll(){
        return orderRepository.findAll().stream().map(orderMapper::toDTO).toList();
    }

    @Transactional(readOnly = true)
    @GetMapping("/{id}")
    public OrderDTO findById(Long id){
        Order order = orderOrThrow(id);
        return orderMapper.toDTO(order);
    }

    @PostMapping("/{id}")
    public OrderDTO createOrder(OrderDTO dto){
        Order order = new Order();
        order
    }

    public OrderDTO updateOrder(Long id, OrderDTO dto){
        Order order = orderOrThrow(id);
        dto.setUserId(order.getUser().getId());
        dto.setCreatedAt(order.getCreatedAt());
        dto.setTotalPrice(order.getTotalPrice());
        dto.setOrderItems(order.getOrderItems().stream().map().toList());
        dto.setOrderStatus(order.getOrderStatus());
    }

    @Transactional(readOnly = true)
    public Order orderOrThrow(Long id){
        if(id == null){
            throw new IllegalArgumentException("Order cannot be null");
        }
        return orderRepository.findById(id).orElseThrow(() -> new OrderNotFoundException(id));
    }
}

