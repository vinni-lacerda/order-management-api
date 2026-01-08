package com.vinilab.OrderManagement.entities;
import com.vinilab.OrderManagement.enums.OrderStatus;
import jakarta.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "tb_order")
public class Order implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL,orphanRemoval = true)
    private List<OrderItem> orderItems = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OrderStatus orderStatus;

    @Column(nullable = false)
    private BigDecimal totalPrice;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    public Order() {
    }

    public Order(User user, BigDecimal totalPrice) {
        this.user = user;
        updateTotalPrice(totalPrice);
        this.createdAt = LocalDateTime.now();
        this.orderStatus = OrderStatus.CREATED;
    }

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        if(orderStatus == null){
            throw new IllegalArgumentException("Invalid order status");
        }
        this.orderStatus = orderStatus;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void updateTotalPrice(BigDecimal totalPrice) {
       if(totalPrice == null || totalPrice.compareTo(BigDecimal.ZERO) < 0){
           throw new IllegalArgumentException("price must be 0 or greater");
       }
        this.totalPrice = totalPrice;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Order order)) return false;
        return Objects.equals(id, order.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}