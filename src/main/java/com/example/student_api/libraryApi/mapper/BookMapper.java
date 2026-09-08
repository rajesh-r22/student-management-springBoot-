package com.example.student_api.libraryApi.mapper;

import com.example.student_api.libraryApi.dto.BookDto;
import com.example.student_api.libraryApi.entity.Book;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;


@Mapper(componentModel = "spring")
@Component("bookMapper")
public interface BookMapper {
    BookDto toDto( Book book);
    Book toEntity(BookDto bookDto);
}
