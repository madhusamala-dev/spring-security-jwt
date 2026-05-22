package com.trainingmug.ecommerce.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthResponseDto {
    String accessToken;

    String refreshToken;

    UserResponseDto user;
}
