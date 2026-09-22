package com.example.student_api.userApi.service;

import com.example.student_api.userApi.dto.ProfileDto;
import com.example.student_api.userApi.dto.UserDetailDto;

public interface UserService {
    ProfileDto createProfile(Long userId,ProfileDto dto);
    UserDetailDto getUserWithProfile(Long userId);

}
