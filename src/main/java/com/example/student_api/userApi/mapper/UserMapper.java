package com.example.student_api.userApi.mapper;

import com.example.student_api.userApi.dto.ProfileDto;
import com.example.student_api.userApi.dto.UserDetailDto;
import com.example.student_api.userApi.entity.Profile;
import com.example.student_api.userApi.entity.User;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component("userMapper")
public interface UserMapper {
    ProfileDto toDto(Profile profile);
    UserDetailDto toDetailDto(User user); // MapStruct auto-wires the nested Profile -> ProfileDTO mapping via the method above

}
