package com.example.student_api.dto;

import lombok.Data;
// Used for LIST views — no nested books, keeps the response light
@Data
public class AuthorDto {
    private Long id;
    private String name;
    private String email;
    private int bookCount; // a computed summary field, NOT the full list

}
