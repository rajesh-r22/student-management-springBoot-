package com.example.student_api.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = RollNumberValidator.class)  // points to the actual logic
public @interface ValidRollNumber {
    String message() default "Roll number must follow format: 2 letters + 4 digits (e.g., CS1023)";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}