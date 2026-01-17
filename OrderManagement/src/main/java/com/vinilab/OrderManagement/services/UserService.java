package com.vinilab.OrderManagement.services;

import com.vinilab.OrderManagement.dtos.UserDTO;

import com.vinilab.OrderManagement.entities.User;
import com.vinilab.OrderManagement.mappers.UserMapper;
import com.vinilab.OrderManagement.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public UserDTO findById(Long id){
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Id must be valid"));
        return userMapper.toDTO(user);
    }

    public List<UserDTO> findAllUsers(){
        return userRepository.findAll().stream().map(userMapper::toDTO).toList();
    }

    public UserDTO updateUser(Long id, UserDTO dto){
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Id must be valid"));
        user.changeName(dto.getName());
        user.changeEmail(dto.getEmail());
        User updatedUser = userRepository.save(user);

        return userMapper.toDTO(updatedUser);
    }

    public void deleteUser(Long id){
        if(!userRepository.existsById(id)){
            throw new IllegalArgumentException("Id must be valid");
        }
        userRepository.deleteById(id);
    }
}
