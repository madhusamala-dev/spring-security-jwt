package com.trainingmug.ecommerce.controller;

import com.trainingmug.ecommerce.dto.AuthResponseDto;
import com.trainingmug.ecommerce.dto.LoginRequestDto;
import com.trainingmug.ecommerce.dto.SignupRequestDto;
import com.trainingmug.ecommerce.dto.UserResponseDto;
import com.trainingmug.ecommerce.exception.InvalidCredentialsException;
import com.trainingmug.ecommerce.exception.UserExistsException;
import com.trainingmug.ecommerce.exception.UserNotFoundException;
import com.trainingmug.ecommerce.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

@RequestMapping("/api/auth")

@RequiredArgsConstructor

public class AuthController {

    private final AuthService authService;

    /*
        LOGIN
     */

    @PostMapping("/login")

    public ResponseEntity<AuthResponseDto>
    login(

            @RequestBody
            LoginRequestDto loginRequestDto

    ) throws UserNotFoundException,
            InvalidCredentialsException {

        return ResponseEntity.ok(

                authService.login(
                        loginRequestDto
                )
        );
    }

    /*
        SIGNUP
     */

    @PostMapping("/signup")

    public ResponseEntity<UserResponseDto>
    signup(

            @RequestBody
            SignupRequestDto signupRequestDto

    ) throws UserExistsException {

        return ResponseEntity.status(HttpStatus.CREATED)

                .body(

                        authService.signup(
                                signupRequestDto
                        )
                );
    }
}
