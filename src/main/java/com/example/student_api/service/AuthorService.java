package com.example.student_api.service;

import com.example.student_api.library.dto.AuthorDetailsDto;
import com.example.student_api.library.dto.BookDto;

public interface AuthorService {
    AuthorDetailsDto getAuthorWithBooks(Long authorId);
    BookDto addBookToAuthor(Long authorId, BookDto bookDto);
    void removeBookFromAuthor(Long authorId, Long bookId);
}
