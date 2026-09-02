package com.example.student_api.student.entity;

import com.vladmihalcea.hibernate.type.json.JsonType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import java.time.LocalDateTime;

@Entity
@Table(name = "students")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long  id;

    @Column(nullable = false,length = 100)
    private String name;

    @Column(nullable = false,length = 100,unique = true)
    private String email;

    private Integer age;

    private String passwordHash;

    @Column(name="created_At")
    private LocalDateTime createdAt;

    @Column(name="created_By")
    private String createdBy;

    @Column(columnDefinition = "jsonb")
    @Type(JsonType.class)
    private String preferences; // stored as JSON string, PostgreSQL validates/indexes it as JSONB
}

