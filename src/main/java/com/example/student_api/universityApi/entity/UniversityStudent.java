package com.example.student_api.universityApi.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="uni_student")
@Data
@NoArgsConstructor
public class UniversityStudent {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Override
    public String toString() {
        return "UniversityStudent{id=" + id + ", name='" + name + "'}"; // avoid recursion once Enrollment is added below
    }
}
