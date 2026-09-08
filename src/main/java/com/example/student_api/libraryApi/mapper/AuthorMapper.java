package com.example.student_api.libraryApi.mapper;

import com.example.student_api.libraryApi.dto.AuthorDetailsDto;
import com.example.student_api.libraryApi.dto.AuthorDto;
import com.example.student_api.libraryApi.entity.Author;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring", uses= BookMapper.class)
@Component("authorMapper")
public interface AuthorMapper {
    @Mapping(target = "bookCount",expression = "java(author.getBooks().size())")
    AuthorDto toDto(Author author);
    Author toEntity(AuthorDto authorDto);

    AuthorDetailsDto toDetailsDto(Author author);// uses BookMapper automatically for the nested list, via `uses = BookMapper.class`
    Author toEntity(AuthorDetailsDto authorDetailsDto);
}
