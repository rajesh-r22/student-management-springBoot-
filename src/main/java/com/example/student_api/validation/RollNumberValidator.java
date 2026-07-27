package com.example.student_api.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class RollNumberValidator implements ConstraintValidator<ValidRollNumber, String> {

    private static final String PATTERN = "^[A-Z]{2}[0-9]{4}$";

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return true; // let @NotNull/@NotBlank handle "required" separately — single-responsibility per annotation
        }
        return value.matches(PATTERN);
    }
}
