package com.vinilab.OrderManagement.repositories;

import com.vinilab.OrderManagement.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
