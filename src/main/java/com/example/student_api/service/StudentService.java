package com.example.student_api.service;

import com.example.student_api.dto.PagedResponse;
import com.example.student_api.dto.StudentDto;
import com.example.student_api.dto.StudentRankDto;
import com.example.student_api.dto.StudentRankProjection;

import java.util.List;

public interface StudentService {

    StudentDto createStudent(StudentDto studentDto);
    StudentDto getStudentById(Long id);
    PagedResponse<StudentDto> getAllStudents(int page, int size, String sortBy, String direction);
    StudentDto updateStudentById(Long id, StudentDto studentDto);
    void deleteStudentById(Long id);
    List<StudentDto> getStudentByName(String name);
    List<StudentDto> getStudentInAgeRange( Integer minAge,  Integer maxAge);
    List<StudentRankDto> getStudentsByAgeRank();
    StudentDto IncrementAge(Long id);

}
