package com.example.student_api.universityApi.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Entity
@Table(name = "enrollments")
@Data
@NoArgsConstructor
public class Enrollment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "universityStudent_id", nullable = false)
    public UniversityStudent universityStudent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="course_id", nullable=false)
    public Course course;

    @Column(name = "enrollment_date", nullable = false)
    private LocalDate enrollmentDate;

    private String grade;

    @Override
    public String toString(){
        return "Enrollment{id" + id + "enrollmentDate" + enrollmentDate + "grade" + grade + "}";
    }
}
