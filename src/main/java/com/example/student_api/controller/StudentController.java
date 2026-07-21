package com.example.student_api.controller;

import com.example.student_api.dto.StudentDto;
import com.example.student_api.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private final StudentService studentService;
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("/add")
    public ResponseEntity<StudentDto> addStudent(@Valid @RequestBody StudentDto studentDto) {
        StudentDto created= studentService.createStudent(studentDto);
        return  new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<StudentDto> getStudentById(@PathVariable Long id){
        StudentDto studentDto = studentService.getStudentById(id);
        return  ResponseEntity.ok(studentDto);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<StudentDto>> getAllStudents(){
        List<StudentDto> studentDtos = studentService.getAllStudents();
        return  ResponseEntity.ok(studentDtos);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentDto> updateStudentById(@PathVariable Long id, @RequestBody StudentDto studentDto) {
        StudentDto updated= studentService.updateStudentById(id, studentDto);
        return  ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudentById(@PathVariable Long id){
        studentService.deleteStudentById(id);
        return  ResponseEntity.noContent().build();
    }

}
