package com.trainingmug.ecommerce.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ApiResponseDto<T> {
    boolean success;
    String message;
    int statusCode;
    T data;

}
