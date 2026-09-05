package com.example.student_api.student.dto;

public interface StudentRankProjection {
    Long getId();
    String getName();
    Integer getAge();
    Integer getRank(); // must match the SQL column alias (case-insensitive, underscore→camelCase)
}

