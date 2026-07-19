package com.example.student_api.service;

import com.example.student_api.dto.StudentDto;

import java.util.List;

public interface StudentService {

    StudentDto createStudent(StudentDto studentDto);
    StudentDto getStudentById(Long id);
    List<StudentDto> getAllStudents();
    StudentDto updateStudentById(Long id, StudentDto studentDto);
    void deleteStudentById(Long id);
}
