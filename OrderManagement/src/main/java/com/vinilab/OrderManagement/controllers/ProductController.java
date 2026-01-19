package com.vinilab.OrderManagement.controllers;

import com.vinilab.OrderManagement.dtos.ProductDTO;
import com.vinilab.OrderManagement.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(name = "api/v1/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<ProductDTO>> findAll(){
        List<ProductDTO> dtos = productService.findAll();

        return ResponseEntity.ok().body(dtos);
    }
    
    @GetMapping(value = "/{id}")
    public ResponseEntity<ProductDTO> findById(@PathVariable Long id){
        ProductDTO dto = productService.findById(id);
        return ResponseEntity.ok().body(dto);
    }

    @PostMapping
    public ResponseEntity<ProductDTO> createProduct(@RequestBody @Valid ProductDTO dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.createProduct(dto));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable Long id,@RequestBody @Valid ProductDTO dto){
        ProductDTO entity = productService.findById(id);
        entity.setName(dto.getName());
        entity.setPrice(dto.getPrice());
        entity.setStockQuantity(dto.getStockQuantity());
        return ResponseEntity.ok().body(entity);
    }
    
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id){
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
    
}
