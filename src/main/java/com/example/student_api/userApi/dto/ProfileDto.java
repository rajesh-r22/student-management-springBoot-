package com.example.student_api.userApi.dto;

import lombok.Data;

@Data
public class ProfileDto {
    private Long id;
    private String bio;
    private String phoneNumber;
    // no "user" field — avoids recursion, same principle as Q11/Q12
}
