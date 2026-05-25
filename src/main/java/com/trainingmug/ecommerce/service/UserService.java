package com.trainingmug.ecommerce.service;


import com.trainingmug.ecommerce.dto.UserResponseDto;
import com.trainingmug.ecommerce.entity.User;
import com.trainingmug.ecommerce.exception.UserNotFoundException;

public interface UserService {
    UserResponseDto save(User user) throws UserNotFoundException;
    User findByEmail(String email) throws UserNotFoundException;
    boolean existsByEmail(String email);
}
