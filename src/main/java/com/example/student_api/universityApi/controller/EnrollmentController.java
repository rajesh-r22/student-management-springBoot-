package com.example.student_api.universityApi.controller;

import com.example.student_api.common.response.ApiResponse;
import com.example.student_api.universityApi.dto.EnrollmentDto;
import com.example.student_api.universityApi.service.EnrollmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/enrollment")
@RequiredArgsConstructor
public class EnrollmentController {

    private final EnrollmentService enrollmentService;

    @PostMapping
    public ResponseEntity<ApiResponse<EnrollmentDto>> enroll(@RequestParam Long universityStudentId, @RequestParam Long courseId) {
        EnrollmentDto dto=enrollmentService.enrollStudent(universityStudentId, courseId);
        return new ResponseEntity<>(ApiResponse.success("enrolled successfully", dto), HttpStatus.CREATED);
    }

    @DeleteMapping
    public ResponseEntity<ApiResponse<Void>> unenroll(@RequestParam Long universityStudentId, @RequestParam Long courseId) {
        enrollmentService.unenrollStudent(universityStudentId, courseId);
        return ResponseEntity.ok(ApiResponse.success("unenrolled successfully", null));
    }

    @GetMapping("/student/{universityStudentId}")
    public ResponseEntity<ApiResponse<List<EnrollmentDto>>> getForStudent(@RequestParam Long universityStudentId, @RequestParam Long courseId) {
        List<EnrollmentDto> list=enrollmentService.getEnrollmentsForStudents(universityStudentId);
        return ResponseEntity.ok(ApiResponse.success("Enrollment fetched successfully", list));
    }

}
