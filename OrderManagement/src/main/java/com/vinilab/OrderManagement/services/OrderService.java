package com.vinilab.OrderManagement.services;

import com.vinilab.OrderManagement.dtos.OrderDTO;
import com.vinilab.OrderManagement.entities.Order;
import com.vinilab.OrderManagement.entities.OrderItem;
import com.vinilab.OrderManagement.entities.Product;
import com.vinilab.OrderManagement.exceptions.OrderItemNotFoundException;
import com.vinilab.OrderManagement.exceptions.OrderNotFoundException;
import com.vinilab.OrderManagement.mappers.OrderItemMapper;
import com.vinilab.OrderManagement.mappers.OrderMapper;
import com.vinilab.OrderManagement.repositories.OrderRepository;
import com.vinilab.OrderManagement.repositories.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final ProductRepository productRepository;

    public OrderService(OrderRepository orderRepository, OrderMapper orderMapper, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
        this.productRepository = productRepository;
    }


    public List<OrderDTO> findAll(){
        return orderRepository.findAll().stream().map(orderMapper::toDTO).toList();
    }

    @Transactional(readOnly = true)
    public OrderDTO findById(Long id){
        Order order = orderOrThrow(id);
        return orderMapper.toDTO(order);
    }

    public OrderDTO addItem(Long orderId, Long productId, Integer quantity){
       Order order = orderOrThrow(orderId);
       Product product = productRepository.getReferenceById(productId);
        OrderItem item = new OrderItem(
                quantity,
                order,
                product,
                product.getPrice()
        );

        order.addOrderItem(item);

        orderRepository.save(order);
        return orderMapper.toDTO(order);
    }

    public OrderDTO updateItemQuantity(Long orderId, Long itemId, Integer quantity){
        Order order = orderOrThrow(orderId);
        OrderItem item = order.getOrderItems().stream().filter(i -> i.getId().equals(itemId)).findFirst().orElseThrow(() -> new OrderItemNotFoundException(itemId));

        item.updateQuantity(quantity);
        return orderMapper.toDTO(order);
    }

    public OrderDTO updateOrder(Long id, OrderDTO dto){
        Order order = orderOrThrow(id);

        return new OrderDTO(
        order.getUser().getId(),
        order.getOrderItems().stream().map(OrderItemMapper::toDTO).toList(),
        order.getOrderStatus(),
        order.getTotalPrice(),
        order.getCreatedAt()
        );
    }

    public OrderDTO removeItem(Long id, Long itemId){
        Order order = orderOrThrow(id);

        OrderItem item = order.getOrderItems().stream()
                .filter(i -> i.getId().equals(itemId))
                .findFirst()
                .orElseThrow(() -> new OrderItemNotFoundException(itemId));

        order.removeOrderItem(item);

        return orderMapper.toDTO(order);
    }

    public void removeOrder(Long id){
        Order order = orderOrThrow(id);
        orderRepository.delete(order);
    }

    @Transactional(readOnly = true)
    public Order orderOrThrow(Long id){
        if(id == null){
            throw new IllegalArgumentException("Order item id cannot be null");
        }
        return orderRepository.findById(id).orElseThrow(() -> new OrderNotFoundException(id));
    }
}

