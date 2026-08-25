package com.example.student_api.dto;

import lombok.Data;

@Data
public class BooksDto {
    private Long  id;
    private String title;
    private String isbn;
    // NOTE: no "author" field here — when listing a book, we don't need to re-embed its author
}
