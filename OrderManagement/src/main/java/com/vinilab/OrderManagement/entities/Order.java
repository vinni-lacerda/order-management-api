package com.vinilab.OrderManagement.entities;
import com.vinilab.OrderManagement.enums.OrderStatus;
import jakarta.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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

    public Order(User user) {
        if(user == null){throw new IllegalArgumentException("Order must have a user");}
        this.user = user;
        this.totalPrice = BigDecimal.ZERO;
        this.createdAt = LocalDateTime.now();
        this.orderStatus = OrderStatus.CREATED;
    }

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    private void updateTotalPrice(BigDecimal totalPrice) {
       if(totalPrice == null || totalPrice.compareTo(BigDecimal.ZERO) < 0){
           throw new IllegalArgumentException("price must be 0 or greater");
       }
        this.totalPrice = totalPrice;
    }

    public List<OrderItem> getOrderItems() {
        return Collections.unmodifiableList(orderItems);
    }

    public void addOrderItem(OrderItem orderItem) {
        if(orderItem == null){
            throw new IllegalArgumentException("Order item is null");
        }
        orderItem.assignToOrder(this);
        this.orderItems.add(orderItem);
    }

    public void removeOrderItem(OrderItem item){
        if(item == null){
            throw new IllegalArgumentException("Order item is null");
        }
        this.orderItems.remove(item);
        item.assignToOrder(null);
    }

    public void payOrder(){
        if(this.orderStatus != OrderStatus.CREATED){
            throw new IllegalStateException("Order cannot be paid");
        }
        this.orderStatus = OrderStatus.PAID;
    }

    public void cancelOrder(){
        if(this.orderStatus == OrderStatus.CANCELLED ){
            throw new IllegalStateException("Order already cancelled");
        }
        this.orderStatus = OrderStatus.CANCELLED;
    }
    public void shipOrder(){
        if(this.orderStatus != OrderStatus.PAID){
            throw new IllegalStateException("Order must be created and/or paid before shipping");
        }
        this.orderStatus = OrderStatus.SHIPPED;
    }
    public void markOrderAsDelivered(){
        if(this.orderStatus != OrderStatus.SHIPPED){
            throw new IllegalStateException("Order must be shipped before");
        }
        this.orderStatus = OrderStatus.DELIVERED;
    }

    public void changeStatus(OrderStatus orderStatus){
        if(orderStatus == null){
            throw new IllegalArgumentException("order status cannot be null");
        }

        if(orderStatus == OrderStatus.CANCELLED){
            throw new IllegalStateException("Cancelled order cannot change status");
        }
        this.orderStatus = orderStatus;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if (!(o instanceof Order)) return false;
        Order other = (Order) o;
        return id != null && id.equals(other.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}