package com.vinilab.OrderManagement.dtos;

import com.vinilab.OrderManagement.entities.Order;
import com.vinilab.OrderManagement.entities.Product;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;

public class OrderItemDTO {

    private Long id;

    @NotNull(message = "quantity cannot be null")
    @Positive(message = "quantity must greater than zero")
    private Integer quantity;

    @NotNull(message = "product cannot be null")
    private Long productId;

    @NotNull(message = "unit price cannot be null")
    @Positive(message = "Price must be greater than zero")
    private BigDecimal unitPrice;

    private BigDecimal subTotal;

    public OrderItemDTO() {
    }

    public OrderItemDTO(Long id, Integer quantity, Long productId,BigDecimal unitPrice, BigDecimal subTotal) {
        this.id = id;
        this.quantity = quantity;
        this.productId = productId;
        this.unitPrice = unitPrice;
        this.subTotal = subTotal;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public BigDecimal getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(BigDecimal subTotal) {
        this.subTotal = subTotal;
    }
}
