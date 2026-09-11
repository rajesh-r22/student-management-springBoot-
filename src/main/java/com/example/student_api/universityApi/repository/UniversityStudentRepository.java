package com.example.student_api.universityApi.repository;

import com.example.student_api.universityApi.entity.UniversityStudent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UniversityStudentRepository extends JpaRepository<UniversityStudent, Long> {
    boolean existsByEmail(String email);
}
