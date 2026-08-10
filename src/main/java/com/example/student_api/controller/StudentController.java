package com.example.student_api.controller;

import com.example.student_api.dto.PagedResponse;
import com.example.student_api.dto.StudentDto;
import com.example.student_api.dto.StudentRankDto;
import com.example.student_api.entity.Student;
import com.example.student_api.service.StudentService;
import com.example.student_api.validation.ValidationGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
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
    public ResponseEntity<StudentDto> addStudent(
            @Validated(ValidationGroup.onCreate.class) @RequestBody StudentDto studentDto) {
        StudentDto created= studentService.createStudent(studentDto);
        return  new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<StudentDto> getStudentById(@PathVariable Long id){
        StudentDto studentDto = studentService.getStudentById(id);
        return  ResponseEntity.ok(studentDto);
    }

    @GetMapping("/getAll")
    public ResponseEntity<PagedResponse<StudentDto>> getAllStudents(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String direction) {

        return ResponseEntity.ok(studentService.getAllStudents(page, size, sortBy, direction));
    }
    @GetMapping("/search")
    public ResponseEntity<List<StudentDto>> getStudentByName(@RequestParam String name) {
        return ResponseEntity.ok(studentService.getStudentByName(name));
    }

    @GetMapping("/age-range")
    public ResponseEntity<List<StudentDto>> getStudentInAgeRange(@RequestParam Integer minAge, @RequestParam Integer maxAge){
        return ResponseEntity.ok(studentService.getStudentInAgeRange(minAge, maxAge));
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentDto> updateStudentById(
             @PathVariable Long id,@Validated(ValidationGroup.onUpdate.class) @RequestBody StudentDto studentDto) {
        StudentDto updated= studentService.updateStudentById(id, studentDto);
        return  ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudentById(@PathVariable Long id){
        studentService.deleteStudentById(id);
        return  ResponseEntity.noContent().build();
    }

    @GetMapping("/rankByAge")
    public ResponseEntity<List<StudentRankDto>> getStudentsRankByAge(){
        return ResponseEntity.ok(studentService.getStudentsByAgeRank());
    }

    @PutMapping("/{id}/incrementAge")
    public ResponseEntity<StudentDto> incrementAge(@PathVariable Long id){
        StudentDto updated=studentService.IncrementAge(id);
        return  ResponseEntity.ok(updated);
    }

}
