package com.example.student_api.mapper;

import com.example.student_api.dto.AuthorDetailsDto;
import com.example.student_api.dto.AuthorDto;
import com.example.student_api.entity.Author;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses=BookMapper.class)
public interface AuthorMapper {
    @Mapping(target = "bookCount",expression = "java(author.getBooks().size())")
    AuthorDto toDto(Author author);
    Author toEntity(AuthorDto authorDto);

    AuthorDetailsDto toDetailsDto(Author author);// uses BookMapper automatically for the nested list, via `uses = BookMapper.class`
    Author toEntity(AuthorDetailsDto authorDetailsDto);
}
