package com.trainingmug.ecommerce.dto;

import com.trainingmug.ecommerce.enums.Role;
import lombok.Data;

@Data
public class UserResponseDto {

    Integer id;

    String name;

    String email;

    Role role;

}
