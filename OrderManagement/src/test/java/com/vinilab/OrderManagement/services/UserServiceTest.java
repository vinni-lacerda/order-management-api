package com.vinilab.OrderManagement.services;

import com.vinilab.OrderManagement.dtos.UserDTO;
import com.vinilab.OrderManagement.entities.User;
import com.vinilab.OrderManagement.mappers.UserMapper;
import com.vinilab.OrderManagement.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Mock
    private UserMapper userMapper;

    private UserDTO userDTO;
    private User user;

    @BeforeEach
    void setup (){
       userDTO = new UserDTO(
               1L,
              "vinicius",
               "vini@email.com"
      );

       user = new User(
               1L,
               "vinicius",
               "vini@email.com"
       );
    }

    @Test
    void findById_ShouldReturnUserDTO_whenUserExists(){
        Long userId = 1L;
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        when(userMapper.toDTO(user)).thenReturn(userDTO);

        UserDTO result = userService.findById(userId);

        assertNotNull(result);
        assertEquals(userDTO.getId(), result.getId());
        assertEquals(userDTO.getName(), result.getName());
        assertEquals(userDTO.getEmail(), result.getEmail());

        verify(userRepository).findById(userId);
        verify(userMapper).toDTO(user);
    }
}
