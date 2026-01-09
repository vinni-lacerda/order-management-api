package com.vinilab.OrderManagement.entities;

import jakarta.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "tb_order_item")
public class OrderItem implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer quantity;

    @ManyToOne(optional = false)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @ManyToOne(optional = false)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(nullable = false)
    private BigDecimal unitPrice;

    public OrderItem() {
    }

    public OrderItem(Integer quantity, Order order, Product product, BigDecimal unitPrice) {
        if(quantity < 1){
            throw new IllegalArgumentException("Quantity cannot be less than 1");
        }
        this.quantity = quantity;
        if(order == null){
            throw new IllegalArgumentException("Order cannot be null");
        }
        this.order = order;
        if(product == null){
            throw new IllegalArgumentException("Product cannot be null");
        }
        this.product = product;
        if(unitPrice == null || unitPrice.compareTo(BigDecimal.ZERO) <= 0){
            throw new IllegalArgumentException("unit price must be greater than zero");
        }
        this.unitPrice = unitPrice;
    }

    public Product getProduct() {
        return product;
    }

    public Order getOrder() {
        return order;
    }

    public int getQuantity() {
        return quantity;
    }

    public void updateQuantity(int quantity) {
        if(quantity < 1){
            throw new IllegalArgumentException("Quantity cannot be less than 1");
        }
        this.quantity = quantity;
    }
    public void increaseQuantity(int amount){
        updateQuantity(this.quantity + amount);
    }
    
    public Long getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderItem)) return false;
        OrderItem other = (OrderItem) o;
        return id != null && id.equals(other.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
