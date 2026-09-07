package com.example.student_api.mapper;

import com.example.student_api.libraryApi.dto.BookDto;
import com.example.student_api.libraryApi.entity.Book;
import org.mapstruct.Mapper;



@Mapper(componentModel = "spring")
public interface BookMapper {
    BookDto toDto( Book book);
    Book toEntity(BookDto bookDto);
}
