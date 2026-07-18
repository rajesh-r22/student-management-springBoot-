package com.example.student_api.repository;

import com.example.student_api.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    // JpaRepository already gives us save(), findById(), findAll(), deleteById()
    // No code needed yet — Spring Data JPA generates the implementation at runtime
}
