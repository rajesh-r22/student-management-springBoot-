package com.example.student_api.universityApi.service;

import com.example.student_api.common.exception.DuplicateResourceException;
import com.example.student_api.common.exception.ResourceNotFoundException;
import com.example.student_api.universityApi.dto.EnrollmentDto;
import com.example.student_api.universityApi.entity.Course;
import com.example.student_api.universityApi.entity.Enrollment;
import com.example.student_api.universityApi.entity.UniversityStudent;
import com.example.student_api.universityApi.mapper.EnrollmentMapper;
import com.example.student_api.universityApi.repository.CourseRepository;
import com.example.student_api.universityApi.repository.EnrollmentRepository;
import com.example.student_api.universityApi.repository.UniversityStudentRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class EnrollmentServiceImpl implements EnrollmentService {

    private static final Logger log = LoggerFactory.getLogger(EnrollmentServiceImpl.class);

    private final EnrollmentMapper enrollmentMapper;
    private final EnrollmentRepository enrollmentRepository;
    private final UniversityStudentRepository universityStudentRepository;
    private final CourseRepository courseRepository;

    @Override
    @Transactional
    public EnrollmentDto  enrollStudent(Long studentId, Long courseId) {
        UniversityStudent student= universityStudentRepository.findById(studentId)
                .orElseThrow(()-> new ResourceNotFoundException("Student Not Found"+ studentId));

        Course course=courseRepository.findById(courseId)
                .orElseThrow(()-> new ResourceNotFoundException("Course Not Found"+ courseId));

        if(enrollmentRepository.existsByUniversityStudentIdAndCourseId(studentId, courseId)) {
            throw new DuplicateResourceException("Enrollment Already Exists");
        }
        Enrollment enrollment=new Enrollment();
        enrollment.setUniversityStudent(student);
        enrollment.setCourse(course);
        enrollment.setEnrollmentDate(LocalDate.now());

        Enrollment saved=enrollmentRepository.save(enrollment);
        log.info("Enrolled Student {} into Course {}", studentId, courseId);

        return enrollmentMapper.toDto(saved);
    }

}
