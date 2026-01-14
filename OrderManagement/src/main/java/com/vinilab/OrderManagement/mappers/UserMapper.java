package com.vinilab.OrderManagement.mappers;

import com.vinilab.OrderManagement.dtos.UserDTO;
import com.vinilab.OrderManagement.entities.User;

public class UserMapper {

    private UserMapper() {
    }

    public static User userToEntity(UserDTO dto){
        if(dto == null){
            throw new IllegalArgumentException("UserDTO cannot be null");
        }

        return new User(
                dto.getName(),
                dto.getEmail()
        );
    }

    public static UserDTO toDTO(User entity){
        if(entity == null){
            throw new IllegalArgumentException("User cannot be null");
        }
        return new UserDTO(
                entity.getId(),
                entity.getName(),
                entity.getEmail()
        );
    }
}
