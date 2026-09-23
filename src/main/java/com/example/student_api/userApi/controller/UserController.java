package com.example.student_api.userApi.controller;

import com.example.student_api.common.response.ApiResponse;
import com.example.student_api.userApi.dto.ProfileDto;
import com.example.student_api.userApi.dto.UserDetailDto;
import com.example.student_api.userApi.service.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserServiceImpl userService;

    @PostMapping("/{userId}/profile")
    public ResponseEntity<ApiResponse<ProfileDto>> createProfile(@PathVariable  Long userId, @RequestBody ProfileDto dto) {
        ProfileDto created= userService.createProfile(userId,dto);
        return new ResponseEntity<>(ApiResponse.success("profile created successfully",created), HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<ApiResponse<UserDetailDto>> getUser(@PathVariable  Long userId) {
        UserDetailDto fetch = userService.getUserWithProfile(userId);
        return ResponseEntity.ok(ApiResponse.success("user fetched successfully",fetch));
    }
}
