package com.example.student_api.universityApi.repository;

import com.example.student_api.universityApi.entity.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
}
