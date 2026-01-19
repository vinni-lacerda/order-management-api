package com.vinilab.OrderManagement.services;
import com.vinilab.OrderManagement.dtos.ProductDTO;
import com.vinilab.OrderManagement.entities.Product;
import com.vinilab.OrderManagement.exceptions.ProductNotFoundException;
import com.vinilab.OrderManagement.mappers.ProductMapper;
import com.vinilab.OrderManagement.repositories.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public ProductService(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    @Transactional(readOnly = true)
    public ProductDTO findById(Long id){
        return productMapper.toDTO(findProductOrThrow(id));
    }

    @Transactional(readOnly = true)
    public List<ProductDTO> findAll(){
        return productRepository.findAll().stream().map(productMapper::toDTO).toList();
    }

    public ProductDTO createProduct(ProductDTO dto){
        Product product = productMapper.toEntity(dto);
        Product savedProduct = productRepository.save(product);
        return productMapper.toDTO(savedProduct);
    }

    public ProductDTO updateProduct(Long id, ProductDTO dto){
        Product entity = findProductOrThrow(id);
        entity.updateName(dto.getName());
        entity.updateStockQuantity(dto.getStockQuantity());
        entity.changePrice(dto.getPrice());
        return productMapper.toDTO(entity);
    }

    public void deleteProduct(Long id){
        Product product = findProductOrThrow(id);
        productRepository.deleteById(product.getId());
    }
   @Transactional(readOnly = true)
   public Product findProductOrThrow(Long id){
        if(id == null){
            throw new IllegalArgumentException("Product id cannot be null");
        }
       return productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
   }
}
