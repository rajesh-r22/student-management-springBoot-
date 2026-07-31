package com.example.student_api.mapper;

import com.example.student_api.dto.StudentDto;
import com.example.student_api.entity.Student;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")   // generates a Spring @Component implementation automatically
public interface StudentMapper {

    StudentDto toDTO(Student student);   // MapStruct matches passwordHash/createdAt/createdBy are simply absent on DTO — auto-ignored

    @Mapping(target = "passwordHash", ignore = true)   // explicit — even though not needed here, shows intent
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "id", ignore = true)              // id is DB-generated, never set from incoming DTO on create
    Student toEntity(StudentDto dto);

}
