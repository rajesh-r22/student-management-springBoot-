package com.example.student_api.controller;

import com.example.student_api.dto.AuthorDetailsDto;
import com.example.student_api.response.ApiResponse;
import com.example.student_api.service.AuthorService;
import jakarta.persistence.GeneratedValue;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/authors")
@RequiredArgsConstructor
public class AuthorController {
    private final AuthorService authorService;

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<AuthorDetailsDto>> findAuthorById(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.success("Author Fetched",authorService.getAuthorWithBooks(id)));git
    }
}
