package com.example.student_api.library.repository;

import com.example.student_api.library.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author,Long> {
    boolean existsByEmail(String email);
}
