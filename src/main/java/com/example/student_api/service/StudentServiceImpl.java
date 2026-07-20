package com.example.student_api.service;

import com.example.student_api.dto.StudentDto;
import com.example.student_api.entity.Student;
import com.example.student_api.exception.ResourceNotFoundException;
import com.example.student_api.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service

@RequiredArgsConstructor   // Lombok generates constructor for final fields -> constructor injection
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    @Override
    public StudentDto createStudent(StudentDto studentDto) {
        Student student= toEntity(studentDto);
        Student savedStudent = studentRepository.save(student);
        return toDto(savedStudent);
    }

    @Override
    public StudentDto getStudentById(Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Student with this Id not found "+id));
        return toDto(student);
    }

    @Override
    public List<StudentDto> getAllStudents(){
         return studentRepository.findAll()
                 .stream()
                 .map(this::toDto)
                 .collect(Collectors.toList());
    }

    @Override
    public StudentDto updateStudentById(Long id, StudentDto studentDto) {
        Student existing = studentRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Student with this Id not found "+ id));
        existing.setName(studentDto.getName());
        existing.setAge(studentDto.getAge());
        existing.setEmail(studentDto.getEmail());
        studentRepository.save(existing);
        return toDto(existing);
    }

    @Override
    public void deleteStudentById(Long id){
        if(!studentRepository.existsById(id)){
            throw new ResourceNotFoundException("Student with this Id not found "+ id);
        }
        studentRepository.deleteById(id);
    }


//  mapping helpers that helps us to convert dto to entity and entity to Dto
    public Student toEntity(StudentDto dto) {
        Student student = new Student();
        student.setName(dto.getName());
        student.setAge(dto.getAge());
        student.setEmail(dto.getEmail());
        return student;
    }
    public StudentDto toDto(Student student){
        StudentDto studentDto = new StudentDto();
        studentDto.setName(student.getName());
        studentDto.setAge(student.getAge());
        studentDto.setEmail(student.getEmail());
        return studentDto;
    }


}
