package com.example.student_api.mapper;

import com.example.student_api.dto.BookDto;
import org.mapstruct.Mapper;

import java.awt.print.Book;

@Mapper(componentModel = "string")
public interface BookMapper {
    BookDto toDto(Book book);
}
