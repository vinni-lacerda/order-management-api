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

    @Column(nullable = false, precision = 19, scale = 2)
    private BigDecimal unitPrice;

    public OrderItem() {
    }

    public OrderItem(Integer quantity, Order order, Product product, BigDecimal unitPrice) {
        validateQuantity(quantity);
        this.quantity = quantity;
        validateOrder(order);
        this.order = order;
        validateProduct(product);
        this.product = product;
        validateUnitPrice(unitPrice);
        this.unitPrice = unitPrice;
    }


    public Product getProduct() {
        return product;
    }

    public Order getOrder() {
        return order;
    }

    public void assignToOrder(Order order){
        validateOrder(order);
        this.order = order;
    }

    private void validateOrder(Order order){
        if(order == null){
            throw new IllegalArgumentException("Order cannot be null");
        }
    }

    private void validateProduct(Product product){
        if(product == null){
            throw new IllegalArgumentException("Product cannot be null");
        }
    }

    private void validateUnitPrice(BigDecimal unitPrice) {
        if(unitPrice == null || unitPrice.compareTo(BigDecimal.ZERO) <= 0){
            throw new IllegalArgumentException("unit price must be greater than zero");
        }
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void updateQuantity(Integer quantity) {
        validateQuantity(quantity);
        this.quantity = quantity;
    }

    private void validateQuantity(Integer quantity){
        if(quantity == null || quantity < 1 ){
            throw new IllegalArgumentException("Quantity cannot be less than 1");
        }
    }

    public void increaseQuantity(int amount){
        if(amount <= 0 ){
            throw new IllegalArgumentException("Increase amount must be greater than 0");
        }
        updateQuantity(this.quantity + amount);
    }

    public BigDecimal getSubTotal(){
        return unitPrice.multiply(BigDecimal.valueOf(quantity));
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
        return getClass().hashCode();
    }
}
