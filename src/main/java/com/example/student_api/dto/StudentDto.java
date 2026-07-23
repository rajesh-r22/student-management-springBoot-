package com.example.student_api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class StudentDto {
    private Long id;

    @NotNull(message = "Name is required")
    private String name;

    // Structural validation — lives on the DTO, enforced at the Controller boundary
    @NotBlank(message = "Email is required")
    @Email(message = "Email must be valid")
    private String email;

    @Min(value = 1, message = "Age must be positive")
    private Integer age;
}
