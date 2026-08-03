package com.example.student_api.service;

import com.example.student_api.dto.PagedResponse;
import com.example.student_api.dto.StudentDto;

import java.util.List;

public interface StudentService {

    StudentDto createStudent(StudentDto studentDto);
    StudentDto getStudentById(Long id);
    PagedResponse<StudentDto> getAllStudents(int page, int size, String sortBy, String direction);

    StudentDto updateStudentById(Long id, StudentDto studentDto);
    void deleteStudentById(Long id);
}
