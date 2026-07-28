package com.example.student_api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class AddressDto {
    @NotBlank(message = "City cannot be blank")
    private String city;

    @NotBlank(message = "PinCode is required")
    @Pattern(regexp = "^[1-9][0-9]{5}$" , message = "PinCode must be valid 6 digit Indian pinCode")
    private String pinCode;

}
