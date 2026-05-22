package com.trainingmug.ecommerce.service;

import com.trainingmug.ecommerce.dto.AuthResponseDto;
import com.trainingmug.ecommerce.dto.LoginRequestDto;
import com.trainingmug.ecommerce.dto.SignupRequestDto;
import com.trainingmug.ecommerce.dto.UserResponseDto;
import com.trainingmug.ecommerce.exception.InvalidCredentialsException;
import com.trainingmug.ecommerce.exception.UserExistsException;
import com.trainingmug.ecommerce.exception.UserNotFoundException;

public interface AuthService {
    AuthResponseDto login(LoginRequestDto loginRequestDto) throws UserNotFoundException, InvalidCredentialsException;
    UserResponseDto signup(SignupRequestDto signupRequestDto) throws UserExistsException;
}
