package com.vinilab.OrderManagement.controllers;

import com.vinilab.OrderManagement.dtos.UserDTO;
import com.vinilab.OrderManagement.services.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> findAll(){
        List<UserDTO> list = userService.findAllUsers();
        return ResponseEntity.ok().body(list);
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<UserDTO> findById(@PathVariable Long id){
        UserDTO dto = userService.findById(id);
        return ResponseEntity.ok().body(dto);
    }
    @PutMapping(value = "/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Long id, @RequestBody @Valid UserDTO userDTO){
        UserDTO dto  = userService.updateUser(id, userDTO);
        return ResponseEntity.ok().body(dto);
    }
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id){
        UserDTO dto = userService.findById(id);
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
