package com.trainingmug.ecommerce.service.impl;

import com.trainingmug.ecommerce.dto.AuthResponseDto;
import com.trainingmug.ecommerce.dto.LoginRequestDto;
import com.trainingmug.ecommerce.dto.SignupRequestDto;
import com.trainingmug.ecommerce.dto.UserResponseDto;
import com.trainingmug.ecommerce.entity.User;
import com.trainingmug.ecommerce.enums.Role;
import com.trainingmug.ecommerce.exception.InvalidCredentialsException;
import com.trainingmug.ecommerce.exception.UserExistsException;
import com.trainingmug.ecommerce.exception.UserNotFoundException;
import com.trainingmug.ecommerce.security.JwtUtil;
import com.trainingmug.ecommerce.service.AuthService;
import com.trainingmug.ecommerce.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service

@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl
        implements AuthService {

    private final UserService userService;

    private final ModelMapper modelMapper;

    private final PasswordEncoder passwordEncoder;

    private final JwtUtil jwtUtil;

    /*
        LOGIN
     */

    @Override
    public AuthResponseDto login(

            LoginRequestDto loginRequestDto

    ) throws UserNotFoundException,
            InvalidCredentialsException {

        /*
            Find User
         */

        User user =
                userService.findByEmail(
                        loginRequestDto.getEmail()
                );

        /*
            Validate Password
         */
        log.info("{} user fetched from service {}", getClass().getName(), user);
        log.info("{} password match : {}", getClass().getName(), passwordEncoder.matches(loginRequestDto.getPassword(), user.getPassword()));
        log.info("{} password : {}", getClass().getName(), passwordEncoder.encode(loginRequestDto.getPassword()).equals(user.getPassword()));
        if(!passwordEncoder.matches(loginRequestDto.getPassword(), user.getPassword())) {

            throw new InvalidCredentialsException(
                    "Invalid email or password"
            );
        }

        /*
            Generate Access Token
         */

        String accessToken =
                jwtUtil.generateAccessToken(
                        user
                );

        /*
            Generate Refresh Token
         */

        String refreshToken =
                jwtUtil.generateRefreshToken(
                        user
                );

        /*
            Return Response
         */

        return AuthResponseDto.builder()

                .accessToken(accessToken)

                .refreshToken(refreshToken)

                .user(

                        modelMapper.map(
                                user,
                                UserResponseDto.class
                        )
                )

                .build();
    }

    /*
        SIGNUP
     */

    @Override
    public UserResponseDto signup(

            SignupRequestDto signupRequestDto

    ) throws UserExistsException {

        /*
            Check Existing User
         */



           /* userService.findByEmail(
                    signupRequestDto.getEmail()
            );*/
        if(userService.existsByEmail(signupRequestDto.getEmail())) {
            throw new UserExistsException(
                    "User already exists with email : "
                            + signupRequestDto.getEmail()
            );
        }


        /*
            DTO -> ENTITY
         */

        User user =
                modelMapper.map(
                        signupRequestDto,
                        User.class
                );

        //user.setPassword(passwordEncoder.encode(user.getPassword()));



        /*
            Save User
         */

        return userService.save(user);
    }
}
