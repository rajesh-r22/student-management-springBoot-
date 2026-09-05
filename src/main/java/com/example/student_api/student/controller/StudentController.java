package com.example.student_api.student.controller;

import com.example.student_api.dto.PagedResponse;
import com.example.student_api.response.ApiResponse;
import com.example.student_api.student.dto.StudentDto;
import com.example.student_api.student.dto.StudentRankDto;
import com.example.student_api.student.service.StudentService;
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
    public ResponseEntity<ApiResponse<PagedResponse<StudentDto>>> getAllStudents(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String direction) {

        PagedResponse<StudentDto> studentDto=studentService.getAllStudents(page,size,sortBy,direction);
        ApiResponse<PagedResponse<StudentDto>> response=ApiResponse.success("Student fetched successfully",studentDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<ApiResponse<List<StudentDto>>> getStudentByName(@RequestParam String name) {
        List<StudentDto> studentDto=studentService.getStudentByName(name);
        ApiResponse<List<StudentDto>> response=ApiResponse.success("Student fetched successfully by name",studentDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/age-range")
    public ResponseEntity<ApiResponse<List<StudentDto>>> getStudentInAgeRange(@RequestParam Integer minAge, @RequestParam Integer maxAge){
        List<StudentDto> studentDto=studentService.getStudentInAgeRange(minAge,maxAge);
        ApiResponse<List<StudentDto>> response= ApiResponse.success("Student fetched successfully by age",studentDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<StudentDto>> updateStudentById(
            @PathVariable Long id,@Validated(ValidationGroup.onUpdate.class) @RequestBody StudentDto studentDto) {
        StudentDto updated= studentService.updateStudentById(id, studentDto);
        ApiResponse<StudentDto> response=ApiResponse.success("Student updated successfully",updated);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteStudentById(@PathVariable Long id){
        studentService.deleteStudentById(id);
        return   ResponseEntity.ok(ApiResponse.success("Student deleted successfully",null));
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

