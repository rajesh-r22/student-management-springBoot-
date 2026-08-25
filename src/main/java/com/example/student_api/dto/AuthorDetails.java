package com.example.student_api.dto;

import lombok.Data;

import java.util.List;

// Used for SINGLE-author detail view — includes the nested books, ONE level deep only
@Data
public class AuthorDetails {
    private Long id;
    private String name;
    private String email;
    private List<BooksDto>  books; // BookDTO does NOT contain author back-reference -> recursion is structurally impossible
}
