package com.example.student_api.repository;

import com.example.student_api.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    // JpaRepository already gives us save(), findById(), findAll(), deleteById()

    // Spring Data JPA auto-generates: SELECT EXISTS(... WHERE email = ?)
    boolean existsByEmail(String email);

}
