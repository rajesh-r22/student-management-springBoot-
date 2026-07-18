package com.example.student_api.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class StudentDto {
    private Long id;

    @NotNull(message = "Name is required")
    private String name;

    @NotNull(message = "Email is required")
    @NotNull(message = "Email should be valid")
    private String email;

    @Min(value = 1, message = "Age must be positive")
    private Integer age;
}
