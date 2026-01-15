package com.vinilab.OrderManagement.dtos;
import com.vinilab.OrderManagement.enums.OrderStatus;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class OrderDTO {

    private Long id;

    @NotNull(message = "User id is required")
    private Long userId;

    private List<OrderItemDTO> orderItems;

    @NotNull(message = "Order status is required")
    private OrderStatus orderStatus;

    @NotNull
    @PositiveOrZero(message = "Total price must be 0 or greater")
    private BigDecimal totalPrice;

    @NotNull(message = "created at cannot be null")
    private LocalDateTime createdAt;

    public OrderDTO() {
    }

    public OrderDTO(Long id, Long userId, List<OrderItemDTO> orderItems, OrderStatus orderStatus, BigDecimal totalPrice, LocalDateTime createdAt) {
        this.id = id;
        this.userId = userId;
        this.orderItems = orderItems;
        this.orderStatus = orderStatus;
        this.totalPrice = totalPrice;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<OrderItemDTO> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItemDTO> orderItems) {
        this.orderItems = orderItems;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}