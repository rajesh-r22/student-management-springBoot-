package com.example.student_api.universityApi.service;

import com.example.student_api.universityApi.dto.EnrollmentDto;

import java.util.List;

public interface EnrollmentService {
    EnrollmentDto enrollStudent(Long studentId,Long courseId);
    void unenrollStudent(Long studentId,Long courseId);
    List<EnrollmentDto> getEnrollmentsForStudents(Long studentId);
}
