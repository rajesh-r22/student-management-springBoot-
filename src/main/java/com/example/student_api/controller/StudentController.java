package com.example.student_api.controller;

import com.example.student_api.dto.PagedResponse;
import com.example.student_api.dto.StudentDto;
import com.example.student_api.dto.StudentRankDto;
import com.example.student_api.entity.Student;
import com.example.student_api.response.ApiResponse;
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

    @PutMapping
    public ResponseEntity<ApiResponse<StudentDto>> createStudent(
            @Validated(ValidationGroup.onCreate.class) @RequestBody StudentDto studentDto){
        StudentDto created=studentService.createStudent(studentDto);
        ApiResponse<StudentDto> response=ApiResponse.success("Student created successfully",created);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<ApiResponse<StudentDto>> getStudent(@PathVariable long id){
        StudentDto studentDto= studentService.getStudentById(id);
        ApiResponse<StudentDto> response=ApiResponse.success("Student fetched successfully",studentDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
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
         return ResponseEntity.ok(studentService.IncrementAge(id));
    }

    @GetMapping("/emailNotification")
    public ResponseEntity<List<StudentDto>> getStudentsWithEmailNotificationsOn(){
        return ResponseEntity.ok(studentService.getStudentsWithEmailNotificationsOn());
    }

}
