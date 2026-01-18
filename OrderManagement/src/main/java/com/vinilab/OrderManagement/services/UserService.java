package com.vinilab.OrderManagement.services;

import com.vinilab.OrderManagement.dtos.UserDTO;

import com.vinilab.OrderManagement.entities.User;
import com.vinilab.OrderManagement.exceptions.UserNotFoundException;
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
        return userMapper.toDTO(findIdOrThrow(id));
    }

    public List<UserDTO> findAllUsers(){
        return userRepository.findAll().stream().map(userMapper::toDTO).toList();
    }

    public UserDTO updateUser(Long id, UserDTO dto){
        User user = findIdOrThrow(id);
        user.changeName(dto.getName());
        user.changeEmail(dto.getEmail());
        User updatedUser = userRepository.save(user);

        return userMapper.toDTO(updatedUser);
    }

    public void deleteUser(Long id){
        userRepository.deleteById(findIdOrThrow(id).getId());
    }

    public User findIdOrThrow(Long id){
        if(id == null){
            throw new IllegalArgumentException("User must be valid");
        }

        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }
}
