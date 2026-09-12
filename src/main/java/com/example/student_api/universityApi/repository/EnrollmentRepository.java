package com.example.student_api.universityApi.repository;

import com.example.student_api.universityApi.entity.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
    boolean existsByUniversityStudentIdAndCourseId(Long universityStudentId, Long courseId);
    Optional<Enrollment> findByUniversityStudentIdAndCourseId(Long universityStudentId, Long courseId);
    List<Enrollment> findByUniversityStudentId(Long universityStudentId);
    List<Enrollment> findByCourseId(Long courseId);
}
