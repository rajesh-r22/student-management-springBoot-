package com.example.student_api.library.repository;

import com.example.student_api.library.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book,Long> {
    List<Book> findByAuthorId(Long authorId);
}
