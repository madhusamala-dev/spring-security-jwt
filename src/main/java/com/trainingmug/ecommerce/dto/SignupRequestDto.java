package com.trainingmug.ecommerce.dto;

import com.trainingmug.ecommerce.enums.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class SignupRequestDto {
    @NotBlank(message = "Name is required")
    @Size(min = 2, max = 20, message = " name must be between 2 and 20 characters")
    @Pattern(
            regexp = "^[A-Za-z]+(?: [A-Za-z]+)*$",
            message = "Only alphabets and single spaces are allowed"
    )
    String name;
    @NotBlank(message = "Email is required")
    @Pattern(
            regexp = "^[a-zA-Z0-9]+(?:\\.[a-zA-Z0-9]+)*@[a-zA-Z0-9-]+\\.[a-zA-Z]{2,}$",
            message = "Invalid email format. Only letters, numbers, and dots are allowed before @"
    )
    String email;
    @NotBlank(message = "Phone is required")
    @Pattern(
            regexp = "^[6-9]\\d{9}$",
            message = "Phone number must be 10 digits and start with 6, 7, 8, or 9"
    )
    String phone;
    @NotBlank(message = "Password is required")
    @Size(min = 6, max = 20, message = "Password must be between 6 and 20 characters")
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",
            message = "Password must contain at least one uppercase letter, one lowercase letter, one digit, one special character, and be at least 8 characters long"
    )
    String password;
    @NotBlank(message = "Role is required")
    Role role;
}
