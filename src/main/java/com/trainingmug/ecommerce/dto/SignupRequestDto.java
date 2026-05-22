package com.trainingmug.ecommerce.dto;

import com.trainingmug.ecommerce.enums.Role;
import lombok.Data;

@Data
public class SignupRequestDto {
    String name;
    String email;
    String password;
    Role role;
}
