package com.example.student_api.controller;

import com.example.student_api.dto.AuthorDetailsDto;
import com.example.student_api.dto.BookDto;
import com.example.student_api.response.ApiResponse;
import com.example.student_api.service.AuthorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/authors")
@RequiredArgsConstructor
public class AuthorController {
    private final AuthorService authorService;

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<AuthorDetailsDto>> findAuthorById(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.success("Author Fetched",authorService.getAuthorWithBooks(id)));
    }

    @PostMapping("/{id}/books")
    public ResponseEntity<ApiResponse<BookDto>> addBook(@PathVariable Long id, @Valid @RequestBody BookDto bookDto) {
        BookDto created=authorService.addBookToAuthor(id, bookDto);
        return new ResponseEntity<>(ApiResponse.success("Book added",created), HttpStatus.CREATED);
    }
}
