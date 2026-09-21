package com.example.student_api.userApi.dto;

import lombok.Data;

// Used for the "get user with their profile" detail view
@Data
public class UserDetailDto {
    private Long id;
    private String username;
    private String email;
    private ProfileDto profile; // nullable — a user might not have created a profile yet
}