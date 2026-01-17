package com.vinilab.OrderManagement.mappers;

import com.vinilab.OrderManagement.dtos.ProductDTO;
import com.vinilab.OrderManagement.entities.Product;

public class ProductMapper {

    private ProductMapper() {
    }

    public Product toEntity(ProductDTO dto){
        if(dto == null){
            throw new IllegalArgumentException("ProductDTO cannot be null");
        }
        return new Product(
                dto.getName(),
                dto.getPrice(),
                dto.getStockQuantity()
        );
    }

    public ProductDTO toDTO(Product entity){
        if(entity == null){
            throw new IllegalArgumentException("Product cannot be null");
        }
        return new ProductDTO(
                entity.getId(),
                entity.getName(),
                entity.getPrice(),
                entity.getStockQuantity()
        );
    }
}
