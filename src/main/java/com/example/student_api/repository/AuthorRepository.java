package com.example.student_api.repository;

import com.example.student_api.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author,Long> {
    boolean existsByEmail(String email);
}
