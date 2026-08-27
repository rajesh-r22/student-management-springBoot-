package com.example.student_api.mapper;

import com.example.student_api.dto.BookDto;
import com.example.student_api.entity.Book;
import org.mapstruct.Mapper;



@Mapper(componentModel = "spring")
public interface BookMapper {
    BookDto toDto( com.example.student_api.entity.Book book);
    Book toEntity(BookDto bookDto);
}
