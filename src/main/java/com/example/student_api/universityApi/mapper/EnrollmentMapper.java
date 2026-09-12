package com.example.student_api.universityApi.mapper;

import com.example.student_api.universityApi.dto.EnrollmentDto;
import com.example.student_api.universityApi.entity.Enrollment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component("enrollmentMapper")
public interface EnrollmentMapper {

    @Mapping(source="universityStudent.id",target = "universityStudentId")
    @Mapping(source = "universityStudent.name",target = "universityStudentName")
    @Mapping(source = "course.id",target = "courseId")
    @Mapping(source = "course.title",target = "courseTitle")
    EnrollmentDto toDto(Enrollment enrollment);
    Enrollment toEntity(EnrollmentDto enrollmentDto);
}
