package com.example.student_api.dto;

import com.example.student_api.validation.ValidRollNumber;
import com.example.student_api.validation.ValidationGroup;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class StudentDto {
    @Null(groups = ValidationGroup.onCreate.class,message = "Id must not be provided on create")
    @NotNull(groups = ValidationGroup.onUpdate.class , message = "Id is required for update")
    private Long id;

    @NotNull(message = "Name is required")
    private String name;

    // Structural validation — lives on the DTO, enforced at the Controller boundary
    @NotBlank(message = "Email is required")
    @Email(message = "Email must be valid")
    private String email;

    @Min(value = 1, message = "Age must be positive")
    private Integer age;

    @ValidRollNumber   // <-- our custom validator
    private String rollNumber;

    @NotNull(message = "Address is required")
    @Valid              // <-- CRITICAL: without @Valid here, AddressDTO's own constraints are silently skipped
    private AddressDto address;
}
