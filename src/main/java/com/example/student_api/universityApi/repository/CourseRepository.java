package com.example.student_api.universityApi.repository;

import com.example.student_api.universityApi.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
}
