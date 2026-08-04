package com.example.student_api.repository;

import com.example.student_api.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    // JpaRepository already gives us save(), findById(), findAll(), deleteById()

    // Spring Data JPA auto-generates: SELECT EXISTS(... WHERE email = ?)
    boolean existsByEmail(String email);

    // 1. Partial, case-insensitive name search
    List<Student> findByNameContainingIgnoreCase(String name);

    // JPQL with multiple conditions — more readable than a long derived method name
    @Query("SELECT s FROM Student s WHERE s.age BETWEEN :minAge AND :maxAge ORDER BY s.name")
    List<Student> findStudentsInAgeRange(@Param("minAge")Integer minAge, @Param("maxAge")Integer maxAge);

}
