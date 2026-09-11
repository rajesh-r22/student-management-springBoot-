package com.example.student_api.universityApi.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class enrollmentDto {

    private Long id;
    private Long universityStudentId;
    private String UniversityStudentName;
    private Long courseId;
    private String courseTitle;
    private LocalDate enrollmentDate;
    private String grade;

}
