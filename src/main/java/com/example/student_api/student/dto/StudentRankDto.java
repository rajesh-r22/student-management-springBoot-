package com.example.student_api.student.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentRankDto {
    private Long id;
    private String name;
    private Integer age;
    private Integer ageRank;
}
