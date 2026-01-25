package com.vinilab.OrderManagement.controllers;
import com.vinilab.OrderManagement.dtos.OrderDTO;
import com.vinilab.OrderManagement.services.OrderService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/orders")
public class OrderController {

    OrderService orderService;


    @GetMapping
    public ResponseEntity<List<OrderDTO>> findAll(){
        List<OrderDTO> dtos = orderService.findAll();

        return ResponseEntity.ok().body(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDTO> findById(@PathVariable Long id){
        OrderDTO dto = orderService.findById(id);
        return ResponseEntity.ok().body(dto);
    }

    @PostMapping
    public ResponseEntity<OrderDTO> createOrder(@RequestBody @Valid OrderDTO dto){
        return ResponseEntity.ok().body(orderService.createOrder(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderDTO> updateOrder(@PathVariable Long id, @RequestBody @Valid OrderDTO orderDTO){
        OrderDTO dto = orderService.updateOrder(id, orderDTO);
        return ResponseEntity.ok().body(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id){
        orderService.removeOrder(id);
        return ResponseEntity.noContent().build();
    }
}
