package com.example.student_api.libraryApi.controller;

import com.example.student_api.libraryApi.dto.AuthorDetailsDto;
import com.example.student_api.libraryApi.dto.BookDto;
import com.example.student_api.response.ApiResponse;
import com.example.student_api.libraryApi.service.AuthorService;
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

    @DeleteMapping("/{authorId}/books/{bookId}")
    public ResponseEntity<ApiResponse<Void>> deleteBook(@PathVariable Long authorId, @PathVariable Long bookId) {
        authorService.removeBookFromAuthor(authorId, bookId);
        return ResponseEntity.ok(ApiResponse.success("Book deleted",null));
    }
}
